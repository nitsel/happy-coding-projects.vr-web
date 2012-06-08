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
	private final static String	DRIVER	= "org.apache.derby.jdbc.EmbeddedDriver";
	private static String		URL		= "jdbc:derby:";
	protected static Logger		logger	= null;
	protected static Connection	conn	= null;
	protected static Statement	stmt	= null;

	protected static String		database;
	protected String			tableElements;

	static
	{
		System.setProperty("Password", "through@pass");
		logger = LoggerFactory.getLogger(Dao.class);
	}

	public Dao()
	{
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

	protected void getConn()
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			String sql = URL + database + ";create=true";
			conn = DriverManager.getConnection(sql);
			stmt = conn.createStatement();

			logger.info("Get connection: {}", sql);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
