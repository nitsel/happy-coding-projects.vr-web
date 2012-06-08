package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.felix.util.system.DateTimeUtil;

import au.com.bytecode.opencsv.CSVReader;

public class BookDao extends Dao
{
	static
	{
		database = "BookDB";
	}

	public BookDao()
	{
		tableElements = "isbn, isbn13, googleId, title, subTitle, authors, pages, price, language, publishDate, publisher, edition, dimensions, weight, description, editorReviews, imgUrlS, imgUrlM, imgUrlL, amazonUrl, reviewUrl, averageRating, ranking";
	}

	void update(Book book) throws Exception
	{
		String sql = "UPDATE books SET isbn13='" + sqlString(book.getIsbn13()) + "', googleId='"
				+ sqlString(book.getGoogleId()) + "', title='" + sqlString(book.getTitle()) + "', subTitle = '"
				+ sqlString(book.getSubTitle()) + "', authors='" + sqlString(book.getAuthors()) + "', pages="
				+ book.getPages() + ", price = '" + sqlString(book.getPrice()) + "', language ='"
				+ sqlString(book.getLanguage()) + "', publishDate='" + sqlString(book.getPublishDate().toString())
				+ "', publisher ='" + sqlString(book.getPublisher()) + "', edition=" + book.getEdition()
				+ ", dimensions='" + sqlString(book.getDimensions()) + "', weight='" + sqlString(book.getWeight())
				+ "', description = '" + sqlString(book.getDescription()) + "', editorReviews='"
				+ sqlString(book.getEditorReviews()) + "', imgUrlS='" + sqlString(book.getImgUrlS()) + "', imgUrlM='"
				+ sqlString(book.getImgUrlM()) + "', imgUrlL='" + sqlString(book.getImgUrlL()) + "', averageRating ="
				+ book.getAverageRating() + ", ranking='" + sqlString(book.getRanking()) + "' WHERE isbn='"
				+ book.getIsbn() + "'";

		logger.info("Update book: {}", sql);
		stmt.executeUpdate(sql);
	}

	void insert(Book book) throws Exception
	{
		// check if it is already exist
		ResultSet rs = query(book);
		if (rs.next())
		{
			logger.debug("Book (isbn = {}) has already been existing in the database. ", book.getIsbn());
			return;
		}

		// insert into database
		String sql = "INSERT INTO books (" + tableElements + ") VALUES ('" + book.getIsbn() + "', '"
				+ sqlString(book.getIsbn13()) + "', '" + sqlString(book.getGoogleId()) + "', '"
				+ sqlString(book.getTitle()) + "', '" + sqlString(book.getSubTitle()) + "', '"
				+ sqlString(book.getAuthors()) + "', " + book.getPages() + ", '" + sqlString(book.getPrice()) + "', '"
				+ sqlString(book.getLanguage()) + "', '" + sqlString(book.getPublishDate().toString()) + "', '"
				+ sqlString(book.getPublisher()) + "', " + book.getEdition() + ", '" + sqlString(book.getDimensions())
				+ "', '" + sqlString(book.getWeight()) + "', '" + sqlString(book.getDescription()) + "', '"
				+ sqlString(book.getEditorReviews()) + "', '" + sqlString(book.getImgUrlS()) + "', '"
				+ sqlString(book.getImgUrlM()) + "', '" + sqlString(book.getImgUrlL()) + "', '"
				+ sqlString(book.getAmazonUrl()) + "', '" + sqlString(book.getReviewUrl()) + "', "
				+ book.getAverageRating() + ", '" + sqlString(book.getRanking()) + "')";

		logger.info("Insert book: {}", sql);

		stmt.executeUpdate(sql);
	};

	public void delete(Book book) throws Exception
	{
		String sql = "DELETE FROM books WHERE isbn = '" + book.getIsbn() + "'";

		logger.info("Delete book: {}", sql);
		stmt.executeUpdate(sql);
	};

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
			book.setPublishDate(DateTimeUtil.parseStrFromDb(rs.getString("publishDate")));
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
	protected boolean createTables() throws Exception
	{

		String sql = "CREATE TABLE books (isbn VARCHAR(20) PRIMARY KEY, isbn13 VARCHAR(20), googleId VARCHAR(30), title VARCHAR(500) NOT NULL,"
				+ "subTitle VARCHAR(500), authors VARCHAR(200), pages INTEGER, price VARCHAR(50), language VARCHAR(20), publishDate DATE, publisher VARCHAR(300),"
				+ "edition INTEGER, dimensions VARCHAR(100), weight VARCHAR(100), description VARCHAR(2000), editorReviews VARCHAR(2000), "
				+ "imgUrlS VARCHAR(500), imgUrlM VARCHAR(500), imgUrlL VARCHAR(500), amazonUrl VARCHAR(500), reviewUrl VARCHAR(500), averageRating REAL, ranking VARCHAR(100) )";

		logger.info("Create table books: {}", sql);
		return stmt.execute(sql);

	};

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
			book.setPublishDate(DateTimeUtil.parseStrToDb("Jan 1, " + data[3]));

			insert(book);
		}

		br.close();
	}

	@Override
	protected boolean dropTables() throws Exception
	{
		String sql = "DROP TABLE books";
		return stmt.execute(sql);
	}

	public static void main(String[] args) throws Exception
	{
		BookDao dao = new BookDao();
		dao.createTables();
		dao.initDataTable();
	}

}
