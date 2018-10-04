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

/**
 * Class provides connections to a database
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class ConnectionPool implements IConnectionPool{

	private static final String DB_CONNECT_PROPERTY = "db_config";
	private static final String DB_CONNECT_URL = "db.url";
	private static final String DB_CONNECT_LOGIN = "db.login";
	private static final String DB_CONNECT_PASS = "db.pass";
	private static final String DB_CONNECT_DRIVER = "db.driver";
	private static final int DB_CONNECT_POOL_SIZE = 32;

	private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

	/**
	 * queue for containing free connections
	 */
	private static BlockingQueue<Connection> availableConnections=new ArrayBlockingQueue<>(DB_CONNECT_POOL_SIZE);
	/**
	 * queue for containing occupied connections
	 */
	private static BlockingQueue<Connection> usedConnections=new ArrayBlockingQueue<>(DB_CONNECT_POOL_SIZE);
	
	/**
	 * Singleton instance
	 */
	private static ConnectionPool instance;
	
	/**
	 * for interaction with file properties and selection the desired object
	 */
	private static final ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
	private static final String url = rb.getString(DB_CONNECT_URL);
	private static final String login = rb.getString(DB_CONNECT_LOGIN);
	private static final String pass = rb.getString(DB_CONNECT_PASS);
	private static final String driver = rb.getString(DB_CONNECT_DRIVER);
	
	/**
	 * Constructor for initialize ConnectionPool
	 */
	private ConnectionPool() {

		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			logger.error("Error! Соединение не установлено! Error: ", e);
			System.exit(1);
		}
		addConnection();
	}
	
	/**
	 * static method for getting instance from connection pool
	 * @return instance
	 */
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
	
	
	/**
	 * fill connection pool
	 */
	private void addConnection() {

		for (int count = 0; count < DB_CONNECT_POOL_SIZE; count++) {
			availableConnections.add(this.createConnection());  
		}
	}


	/**
	 * gets connection
	 * @return connection
	 */
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

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.connection.IConnectionPool#getConnect()
	 */
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

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.connection.IConnectionPool#disconnect(java.sql.Connection)
	 */
	@Override
	public void disconnect(Connection connection){

		if (!usedConnections.isEmpty()) {
				if(usedConnections.remove(connection)) {
				availableConnections.add(connection);
				}	
			} 
			else {
				throw new NullPointerException("Connection not in the usedConnections array");
			}
	}

}