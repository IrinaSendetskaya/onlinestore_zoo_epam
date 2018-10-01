package by.htp.onlinestore.connection;

import java.sql.Connection;

/**
 * Interface provides connections to a database
 * 
 * @author Iryna Siandzetskaya
 *
 */
public interface IConnectionPool {
	
	/**
	 * Returns connection from connection pool to user
	 * @return connection
	 */
	Connection getConnect();
	/**
	 * destroy connection pool and put the connection to the pool
	 * @param connection
	 */
	void disconnect(Connection connection);

}
