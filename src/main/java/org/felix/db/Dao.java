package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.felix.web.client.AmazonBookClient;
import org.felix.web.client.GoogleBookClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Database Administrator Operator: prepare data tables and initial data
 * 
 * @author guoguibing
 * 
 */
public abstract class Dao
{
	protected static Logger		logger	= null;
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String URL = "jdbc:derby:BookDB";
	
	protected static Connection	conn;
	protected static Statement	stmt;
	
	static
	{
		System.setProperty("Password", "through@pass");
		logger = LoggerFactory.getLogger(Dao.class);

		getConn();
	}

	protected abstract boolean createTable() throws Exception;

	protected abstract boolean dropTable() throws Exception;
	
	protected String replace(String old)
	{
		return old.replace('\'', ' ');
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

	protected void closeConn() throws SQLException
	{
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}

	protected static void getConn()
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(URL + ";create=true");
			stmt = conn.createStatement();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		String title = "A Second Chicken Soup for the Woman's";
		String isbn = "1558746226";

		GoogleBookClient googleBook = new GoogleBookClient();
		String googleId = googleBook.searchBookId(title, isbn);

		if (googleId != null)
		{
			AmazonBookClient amazonBook = new AmazonBookClient();
			Book book = amazonBook.searchBook(isbn);
			book.setGoogleId(googleId);

			List<Review> reviews = amazonBook.searchReviews(isbn, book.getReviewUrl());

			// update to database
		}
	}
}
