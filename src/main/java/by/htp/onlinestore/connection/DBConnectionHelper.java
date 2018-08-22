package by.htp.onlinestore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DBConnectionHelper {

	private static final String DB_CONNECT_PROPERTY="db_config";
	private static final String DB_CONNECT_URL="db.url";
	private static final String DB_CONNECT_LOGIN="db.login";
	private static final String DB_CONNECT_PASS="db.pass";
	private static final String DB_CONNECT_DRIVER="db.driver";
	
	private static final Logger logger=LoggerFactory.getLogger(DBConnectionHelper.class);

	public DBConnectionHelper() {
	
	}
	
	public static Connection connect() {
		
		Connection connection=null;
		
		try {
			ResourceBundle rb=ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
			
			String url=rb.getString(DB_CONNECT_URL);
			String login=rb.getString(DB_CONNECT_LOGIN);
			String pass=rb.getString(DB_CONNECT_PASS);
			
			Class.forName(rb.getString(DB_CONNECT_DRIVER));
			
			if (connection==null||connection.isClosed()) {
			connection=DriverManager.getConnection(url,login,pass);
			}
			
		}  catch (SQLException | ClassNotFoundException e) {
			System.out.println("Соединение не установлено!");
			//log
			logger.error("Error! Соединение не установлено! Error: "+e.getMessage());
		}
		return connection;
	}
	
	public static void disconnect(Connection connection) {
		
		if(connection!=null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				System.out.println("Соединение не закрыто!");
				//log
				logger.error("Error! Соединение не закрыто! Error: "+e.getMessage());
			}
		}
	}
	
}
