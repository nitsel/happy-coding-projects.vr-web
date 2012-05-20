package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Database Administrator Operator: prepare data tables and initial data
 * 
 * @author guoguibing
 * 
 */
public class DBA
{
	private static Logger logger = null;
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String URL = "jdbc:derby:BookDB;create=true";
	
	private Connection conn;
	private Statement stmt;
	
	public DBA()
	{
		System.setProperty("Password", "through@pass");
		logger = LoggerFactory.getLogger(DBA.class);
	}

	public boolean createTable() throws Exception
	{
		try
		{
			String SQL_Drop = "DROP TABLE books";
			stmt.execute(SQL_Drop);
		} catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
		
		String SQL_table = "CREATE TABLE books" + "(isbn VARCHAR(100) PRIMARY KEY," + "title VARCHAR(200) NOT NULL,"
				+ "authors VARCHAR(255) NOT NULL," + "pubYear INTEGER," + "publisher VARCHAR(255),"
				+ "imgUrlS VARCHAR(255)," + "imgUrlM VARCHAR(255)," + "imgUrlL VARCHAR(255)" + " )";

		return stmt.execute(SQL_table);
	}
	
	private String replace(String old)
	{
		return old.replace('\'', '\"');
	}

	public void insertData() throws Exception
	{
		String title = null, isbn = null, authors = null, publisher = null, imgUrlS = null, imgUrlM = null, imgUrlL = null;
		int year = 0;
		
		CSVReader br = new CSVReader(new FileReader(new File("Book-Crossing/BX-Books.csv")), ';', '"', 1);
		String[] data = null;
		while ((data = br.readNext()) != null)
		{
			isbn = data[0];
			title = replace(data[1]);
			authors = replace(data[2]);

			year = Integer.parseInt(data[3]);
			publisher = replace(data[4]);
			imgUrlS = replace(data[5]);
			imgUrlM = replace(data[6]);
			imgUrlL = replace(data[7]);
			
			/* if already exists, skip it */
			String SQL_query = "SELECT * FROM books where isbn = '" + isbn + "'";
			ResultSet rs = stmt.executeQuery(SQL_query);
			if (rs.next()) continue;

			/* otherwise, insert data into table */
			String SQL_insert = new StringBuilder()
					.append("INSERT INTO books (isbn, title, authors, pubYear, publisher, imgUrlS, imgUrlM, imgUrlL) VALUES ('")
					.append(isbn).append("', '").append(title).append("', '").append(authors).append("', ")
					.append(year).append(", '").append(publisher).append("', '").append(imgUrlS).append("', '")
					.append(imgUrlM).append("', '").append(imgUrlL).append("')").toString();

			try
			{
				stmt.executeUpdate(SQL_insert);
			} catch (Exception e)
			{
				logger.debug(SQL_insert);
				e.printStackTrace();
			}
		}
	}

	private void closeConn() throws SQLException
	{
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}

	private void getConn() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER).newInstance();
		conn = DriverManager.getConnection(URL);
		stmt = conn.createStatement();
	}

	public static void main(String[] args) throws Exception
	{
		DBA dba = new DBA();

		dba.getConn();
		// dba.createTable();
		dba.insertData();
		dba.closeConn();
		
		logger.error("Program is Finished!");
	}
}
