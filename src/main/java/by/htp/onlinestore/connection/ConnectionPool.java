package by.htp.onlinestore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

	private static BlockingQueue<Connection> availableConnections=new ArrayBlockingQueue<>(DB_CONNECT_POOL_SIZE);
	private static BlockingQueue<Connection> usedConnections=new ArrayBlockingQueue<>(DB_CONNECT_POOL_SIZE);
	
	private static ConnectionPool instance;
	
	private static final ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
	private static final String url = rb.getString(DB_CONNECT_URL);
	private static final String login = rb.getString(DB_CONNECT_LOGIN);
	private static final String pass = rb.getString(DB_CONNECT_PASS);
	private static final String driver = rb.getString(DB_CONNECT_DRIVER);
	
	private ConnectionPool() {

		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			logger.error("Error! Соединение не установлено! Error: ", e);
			System.exit(1);
		}
		addConnection();
	}
	
	public static IConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}
	
	
	private void addConnection() {

		for (int count = 0; count < DB_CONNECT_POOL_SIZE; count++) {
			availableConnections.add(this.createConnection());  
		}
	}


	private Connection createConnection() {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(url, login, pass);
		} catch (SQLException e) {
			logger.error("Error! Соединение не установлено! Error: ", e);
			System.exit(1);
		}
		return connection;
	}

	@Override
	public Connection getConnect() {

		Connection connection = null;
		
		if (!availableConnections.isEmpty()) {
			try {
				connection=availableConnections.take();
				usedConnections.add(connection);
			} catch (InterruptedException e) {
				logger.error(e.getMessage() + " in getConnect method of ConnectionPool class", e);
			}
			return connection;
		} else {
			logger.error(MessageConstantDeclaration.MSG_ERROR, "All connection used!");
			return null;
		}
	}

	@Override
	public boolean disconnect(Connection connection) {

		if (connection != null) {
			
			try {
				connection=usedConnections.remove();
				availableConnections.add(connection);		
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage() + " in disconnect method of ConnectionPool class", e);
				return false;
			}
			return true;
		}
		return false;
	}

}