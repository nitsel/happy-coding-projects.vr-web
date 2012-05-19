package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Database Administrator Operator: prepare data tables and initial data
 * 
 * @author guoguibing
 * 
 */
public class DBA
{
	private final static String	DRIVER	= "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String	URL		= "jdbc:derby:BookDB;create=true";

	private Connection			conn;
	private Statement			stmt;

	public boolean createTable() throws Exception
	{
		String SQL_Drop = "DROP TABLE books";
		stmt.execute(SQL_Drop);

		String SQL_table = "CREATE TABLE books"
			+ "(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
			  "isbn VARCHAR(100) NOT NULL," +
			  "title VARCHAR(200) NOT NULL,"+
			  "authors VARCHAR(255) NOT NULL," +
			  "publishYear INTEGER," +
			  "publisher VARCHAR(255)," +
			  "imgUrlS VARCHAR(255)," +
			  "imgUrlM VARCHAR(255)," +
			  "imgUrlL VARCHAR(255)" +
			  " )";

		return stmt.execute(SQL_table);
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
			title = data[1];
			authors = data[2];
			year = Integer.parseInt(data[3]);
			publisher = data[4];
			imgUrlS = data[5];
			imgUrlM = data[6];
			imgUrlL = data[7];

			String SQL_insert = new StringBuilder()
					.append("INSERT INTO books ( isbn, title, authors, year, publisher, imgUrlS, imgUrlM, imgUrlL) VALUES( ")
					.append(isbn).append(",").append(title).append(",").append(authors).append(",").append(year)
					.append(",").append(publisher).append(",").append(imgUrlS).append(",").append(imgUrlM).append(",")
					.append(imgUrlL).toString();

			stmt.executeUpdate(SQL_insert);
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
		dba.createTable();
		dba.insertData();
		dba.closeConn();
	}
}
