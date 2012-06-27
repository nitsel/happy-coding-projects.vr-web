package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.felix.system.DateUtils;

import au.com.bytecode.opencsv.CSVReader;

public class BookDao extends DerbyDao
{
	static
	{
		database = "BookDB";
	}

	protected void update(Book book) throws Exception
	{

		String sql = "UPDATE books SET isbn13='" + book.getIsbn13() + "', googleId='" + book.getGoogleId()
				+ "', title='" + book.getTitle() + "', subTitle = '" + book.getSubTitle() + "', authors='"
				+ book.getAuthors() + "', pages=" + book.getPages() + ", price = '" + book.getPrice()
				+ "', language ='" + book.getLanguage() + "', publishDate='" + book.getPublishDate().toString()
				+ "', publisher ='" + book.getPublisher() + "', edition=" + book.getEdition() + ", dimensions='"
				+ book.getDimensions() + "', weight='" + book.getWeight() + "', description = '"
				+ book.getDescription() + "', editorReviews='" + book.getEditorReviews() + "', imgUrlS='"
				+ book.getImgUrlS() + "', imgUrlM='" + book.getImgUrlM() + "', imgUrlL='" + book.getImgUrlL()
				+ "', averageRating =" + book.getAverageRating() + ", ranking='" + book.getRanking() + "' WHERE isbn='"
				+ book.getIsbn() + "'";

		logger.info("Update book: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void insert(Book book) throws Exception
	{
		// check if it is already exist
		ResultSet rs = query(book);
		if (rs.next())
		{
			logger.debug("Book (isbn = {}) has already been existing in the database. ", book.getIsbn());
			return;
		}

		// insert into database
		String tableElements = "isbn, isbn13, googleId, title, subTitle, authors, pages, price, language, publishDate, publisher, edition, dimensions, weight, description, editorReviews, imgUrlS, imgUrlM, imgUrlL, amazonUrl, reviewUrl, averageRating, ranking";
		String sql = "INSERT INTO books (" + tableElements + ") VALUES ('" + book.getIsbn() + "', '" + book.getIsbn13()
				+ "', '" + book.getGoogleId() + "', '" + book.getTitle() + "', '" + book.getSubTitle() + "', '"
				+ book.getAuthors() + "', " + book.getPages() + ", '" + book.getPrice() + "', '" + book.getLanguage()
				+ "', '" + book.getPublishDate().toString() + "', '" + book.getPublisher() + "', " + book.getEdition()
				+ ", '" + book.getDimensions() + "', '" + book.getWeight() + "', '" + book.getDescription() + "', '"
				+ book.getEditorReviews() + "', '" + book.getImgUrlS() + "', '" + book.getImgUrlM() + "', '"
				+ book.getImgUrlL() + "', '" + book.getAmazonUrl() + "', '" + book.getReviewUrl() + "', "
				+ book.getAverageRating() + ", '" + book.getRanking() + "')";

		logger.info("Insert book: {}", sql);

		stmt.executeUpdate(sql);
	}

	public void delete(Book book) throws Exception
	{
		String sql = "DELETE FROM books WHERE isbn = '" + book.getIsbn() + "'";

		logger.info("Delete book: {}", sql);
		stmt.executeUpdate(sql);
	}

	public List<String> retrieveAllBooks() throws Exception
	{
		List<String> results = null;

		String sql = "SELECT * FROM books";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			if (results == null) results = new ArrayList<String>();
			results.add(rs.getString("isbn"));
		}

		return results;
	}

	public ResultSet query(Book book) throws Exception
	{
		String sql = "SELECT * FROM books WHERE isbn = '" + book.getIsbn() + "'";

		logger.info("Query book: {}", sql);
		return stmt.executeQuery(sql);
	}

	public Book query(String isbn) throws Exception
	{
		String sql = "SELECT * FROM books WHERE isbn = '" + isbn + "'";
		logger.info("Query book: {}", sql);

		ResultSet rs = stmt.executeQuery(sql);
		Book book = null;
		if (rs.next())
		{
			book = new Book(isbn);
			book.setIsbn13(rs.getString("isbn13"));
			book.setGoogleId(rs.getString("googleId"));
			book.setTitle(rs.getString("title"));
			book.setSubTitle(rs.getString("subTitle"));
			book.setAuthors(rs.getString("authors"));
			book.setPages(Integer.parseInt(rs.getString("pages")));
			book.setPrice(rs.getString("price"));
			book.setLanguage(rs.getString("language"));
			book.setPublishDate(DateUtils.parseString(rs.getString("publishDate")));
			book.setPublisher(rs.getString("publisher"));
			book.setEdition(Integer.parseInt(rs.getString("edition")));
			book.setDimensions(rs.getString("dimensions"));
			book.setDescription(rs.getString("description"));
			book.setWeight(rs.getString("weight"));
			book.setEditorReviews(rs.getString("editorReviews"));
			book.setImgUrlS(rs.getString("imgUrlS"));
			book.setImgUrlM(rs.getString("imgUrlM"));
			book.setImgUrlL(rs.getString("imgUrlL"));
			book.setAmazonUrl(rs.getString("amazonUrl"));
			book.setReviewUrl(rs.getString("reviewUrl"));
			book.setAverageRating(Float.parseFloat(rs.getString("averageRating")));
			book.setRanking(rs.getString("ranking"));
		}

		return book;
	}

	@Override
	protected void createTables() throws Exception
	{

		String sql = "CREATE TABLE books (isbn VARCHAR(20) PRIMARY KEY, isbn13 VARCHAR(20), googleId VARCHAR(30), title VARCHAR(500) NOT NULL,"
				+ "subTitle VARCHAR(500), authors VARCHAR(200), pages INTEGER, price VARCHAR(50), language VARCHAR(20), publishDate DATE, publisher VARCHAR(300),"
				+ "edition INTEGER, dimensions VARCHAR(100), weight VARCHAR(100), description VARCHAR(2000), editorReviews VARCHAR(2000), "
				+ "imgUrlS VARCHAR(500), imgUrlM VARCHAR(500), imgUrlL VARCHAR(500), amazonUrl VARCHAR(500), reviewUrl VARCHAR(500), averageRating REAL, ranking VARCHAR(100) )";

		logger.info("Create table books: {}", sql);
		stmt.execute(sql);

	};

	/**
	 * Parse string in pattern "September 1, 2004" to java.sql.Date
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public java.sql.Date parseString(String date) throws Exception
	{
		/* check if in the pattern "September 01, 2004" */
		if (date.indexOf(',') < 0)
		{
			// take care "September 2004" or "2004"
			if (date.indexOf(' ') < 0)
			{
				date = "Jan 01, " + date;
			} else
			{
				String month = date.substring(0, date.indexOf(' '));
				String year = date.substring(date.indexOf(' ') + 1);
				date = month + " 01, " + year;
			}
		}

		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG);
		return new java.sql.Date(format.parse(date).getTime());
	}

	protected void initDataTable() throws Exception
	{
		CSVReader br = new CSVReader(new FileReader(new File("Book-Crossing/BX-Books.csv")), ';', '"', 1);
		String[] data = null;

		while ((data = br.readNext()) != null)
		{
			Book book = new Book(data[0]);

			book.setTitle(data[1]);
			book.setAuthors(data[2]);
			book.setPublisher(data[4]);
			book.setImgUrlS(data[5]);
			book.setImgUrlM(data[6]);
			book.setImgUrlL(data[7]);
			book.setPublishDate(parseString("Jan 1, " + data[3]));

			insert(book);
		}

		br.close();
	}

	@Override
	protected void dropTables() throws Exception
	{
		dropTable("books");
	}

	public static void main(String[] args) throws Exception
	{
		BookDao dao = new BookDao();
		dao.createTables();
		dao.initDataTable();
	}

	@Override
	protected boolean clearTables() throws Exception
	{
		String sql = "DELETE FROM books";
		return stmt.execute(sql);
	}

}
