package com.cg.dao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtil {
	private JdbcUtil() {
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties p = new Properties();
		try {
			p.load(new FileReader("src/oracle.ds"));
		} catch (IOException e) {
			throw new SQLException(e.getMessage());
		}
		
		String driver = p.getProperty("driver");
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String pass = p.getProperty("pass");
		//Registering database driver with driver manager
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
		conn = DriverManager.getConnection(url, user, pass);
		return conn;

	}
}
