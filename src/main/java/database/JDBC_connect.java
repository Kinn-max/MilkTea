package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_connect {
	public static Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver( new com.mysql.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/websitets";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
