package org.felix.phd.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * Database Administrator Operator: prepare data tables and initial data
 * 
 * @author guoguibing
 * 
 */
public class DBA
{
	private final static String	DRIVER	= "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String	URL		= "jdbc:derby:bookDB;create=true";

	private Connection			conn;
	private Statement			stmt;

	public DBA() throws Exception
	{
		createTable();
	}

	public boolean createTable() throws Exception
	{
		String SQL_table = "CREATE TABLE books"
			+ "(id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
			  "title VARCHAR(200) NOT NULL"+
			  "isbn VARCHAR(100) NOT NULL," +
			  "authors VARCHAR(255) NOT NULL," +
			  "publishDate DATE," +
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
		Date publishDate = null;
		
		File dataset = new File("Dataset/books.csv");
		BufferedReader br = new BufferedReader(new FileReader(dataset));
		String line = null;
		while ((line = br.readLine()) != null)
		{
			String[] data = line.split("::");
			title = data[0];
			isbn = data[1];
			authors = data[2];
			publishDate = (Date) new SimpleDateFormat().parse(data[3]);
			publisher = data[4];
			imgUrlS = data[5];
			imgUrlM = data[6];
			imgUrlL = data[7];

			String SQL_insert = new StringBuilder()
					.append("INSERT INTO books (title, isbn, authors, publishDate, publisher, imgUrlS, imgUrlM, imgUrlL) VALUES( ")
					.append(title).append(",")
					.append(isbn).append(",").append(authors).append(",").append(publishDate).append(",")
					.append(publisher).append(",").append(imgUrlS).append(",").append(imgUrlM).append(",")
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
		dba.closeConn();
	}
}
