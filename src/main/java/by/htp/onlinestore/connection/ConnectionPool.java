package by.htp.onlinestore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

public class ConnectionPool implements IConnectionPool{

	private static final String DB_CONNECT_PROPERTY = "db_config";
	private static final String DB_CONNECT_URL = "db.url";
	private static final String DB_CONNECT_LOGIN = "db.login";
	private static final String DB_CONNECT_PASS = "db.pass";
	private static final String DB_CONNECT_DRIVER = "db.driver";
	private static final int DB_CONNECT_POOL_SIZE = 32;

	private static final Logger logger = LoggerFactory.getLogger(DBConnectionHelper.class);

	private final Queue<Connection> availableConnections;
	private final Queue<Connection> usedConnections;

	public ConnectionPool() {

		availableConnections = new LinkedBlockingQueue<>(DB_CONNECT_POOL_SIZE);
		usedConnections = new LinkedBlockingQueue<>(DB_CONNECT_POOL_SIZE);
		
		for (int count = 0; count < DB_CONNECT_POOL_SIZE; count++) {
			availableConnections.offer(this.createConnection());  //or add??
		}
	}

	private Connection createConnection() {

		Connection connection = null;

		try {
			ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);

			String url = rb.getString(DB_CONNECT_URL);
			String login = rb.getString(DB_CONNECT_LOGIN);
			String pass = rb.getString(DB_CONNECT_PASS);
			Class.forName(rb.getString(DB_CONNECT_DRIVER));

			connection = DriverManager.getConnection(url, login, pass);

		} catch (SQLException | ClassNotFoundException e) {
			logger.error("Error! Соединение не установлено! Error: ", e);
		}
		return connection;
	}

	@Override
	public Connection getConnect() {

		Connection connection = null;

		if (!availableConnections.isEmpty()) {
			connection = availableConnections.peek();
			availableConnections.poll();
			usedConnections.offer(connection);
			return connection;
		} else {
			logger.error(MessageConstantDeclaration.MSG_ERROR, "All connection used!");
			return null;
		}
	}

	@Override
	public boolean disconnect(Connection connection) {

		if (connection != null && usedConnections.poll() != null) {
			availableConnections.offer(connection);
			return true;
		}
		return false;
	}

	public int getFreeConnectionCount() {
		return availableConnections.size();
	}
}