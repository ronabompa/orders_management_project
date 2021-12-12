package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains the name of the driver (initialized through reflection),
 * the database location (DBURL) the user the password (for accessing the MySQL
 * Server) 
 * 
 * The class contains methods for creating a connection, getting an active
 * connection and closing a connection, a Statement or a ResultSet
 * 
 * @author d.rona
 *
 */

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	// Ce e un Singleton object?

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private Connection createConnection();
    public static Connection getConnecion()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch(SQLException ex)
		{
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}

		return conn;

	}

}
