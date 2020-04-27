package com.processproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author s164166
 *
 */
public class DBConnector 
{
	private static Connection connection;
	
	private static void setupDBConnector() throws ClassNotFoundException, SQLException {
	    Class.forName("org.mariadb.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProcessDB", "root", "");
	}
	
	public static void startConnection()
	{
	    if (connection == null)
	    {
	        try {
	        	setupDBConnector();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public static ResultSet executeQuery(PreparedStatement statement)
	{
		startConnection();
		try {
			return statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int executeUpdate(PreparedStatement statement)
	{
		startConnection();
		try {
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static PreparedStatement createStatement(String sql)
	{
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static void closeConnection()
	{
        try
        {
        	if (!connection.isClosed())
			{
				connection.close();
			}
		}
        catch (SQLException e)
        {
			e.printStackTrace();
		}
	}		

}
