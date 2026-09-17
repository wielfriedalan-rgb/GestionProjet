package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
//	private String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/projects";
	private static final String LOGIN = "postgres";
	private static final String PASSWORD = "123456";
	
	public static Connection getConnectionDB() throws SQLException{
		return DriverManager.getConnection(URL, LOGIN, PASSWORD);
	}
}
