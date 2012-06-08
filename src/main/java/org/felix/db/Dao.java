package org.felix.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract Data Access Object
 * 
 * @author guoguibing
 * 
 */
public abstract class Dao
{
	protected static Logger		logger	= null;
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String		URL		= "jdbc:derby:";
	
	protected static Connection	conn;
	protected static Statement	stmt;
	protected static String		database;
	protected static String		tableElements;
	
	static
	{
		System.setProperty("Password", "through@pass");
		logger = LoggerFactory.getLogger(Dao.class);

		getConn();
	}

	protected abstract boolean createTable() throws Exception;

	protected abstract boolean dropTable() throws Exception;
	
	protected void closeConn() throws SQLException
	{
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}

	protected String sqlString(String normalStr)
	{
		return normalStr == null ? "" : normalStr;
	}

	protected static void getConn()
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(URL + database + ";create=true");
			stmt = conn.createStatement();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
