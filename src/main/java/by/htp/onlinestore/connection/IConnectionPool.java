package by.htp.onlinestore.connection;

import java.sql.Connection;

public interface IConnectionPool {
	
	Connection getConnect();
	boolean disconnect(Connection connection);

}
