package org.felix.db;

import java.sql.ResultSet;

public class BookDao extends Dao
{
	private final static String	tableElements	= "isbn, isbn13, googleId, title, subTitle, authors, "
														+ "pages, price, language, publishDate, publisher, edition, dimensions, weight, description, "
														+ "editorReviews, imgUrlS, imgUrlM, imgUrlL, amazonUrl, reviewUrl, averageRating, ranking";

	void update(Book book)
	{};

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
				+ book.getIsbn13() + "', '" + book.getGoogleId() + "', '" + book.getTitle() + "', '" 
				+ book.getSubTitle() + "', '" + book.getAuthors() + "', " + book.getPages() + ", '" 
				+ book.getPrice() + "', '" + book.getLanguage()	+ "', " + book.getPublishDate() + ", '" 
				+ book.getPublisher() + "', " + book.getEdition() + ", '"
				+ book.getDimensions() + "', " + book.getWeight() + ", '" + book.getDescription() + "', '"
				+ book.getEditorReviews() + "', '" + book.getImgUrlS() + "', '" + book.getImgUrlM() + "', '"
				+ book.getImgUrlL() + "', '" + book.getReviewUrl() + "', " + book.getAverageRating() + ", "
				+ book.getRanking() + ", ";

		logger.debug("Insert book: {}", sql);

		stmt.executeUpdate(sql);
	};

	public void delete(Book book) throws Exception
	{
		String sql = "DELETE FROM books WHERE isbn = '" + book.getIsbn() + "'";

		logger.debug("Delete book: {}", sql);
		stmt.executeUpdate(sql);
	};

	public ResultSet query(Book book) throws Exception
	{
		String sql = "SELECT * FROM books WHERE isbn = '" + book.getIsbn() + "'";

		logger.debug("Query book: {}", sql);
		return stmt.executeQuery(sql);
	}

	@Override
	public boolean createTable() throws Exception
	{

		String sql = "CREATE TABLE books (isbn VARCHAR(20) PRIMARY KEY, isbn13 VARCHAR(20), googleId VARCHAR(30), title VARCHAR(500) NOT NULL,"
				+ "subTitle VARCHAR(500), authors VARCHAR(200), pages INTEGER, price VARCHAR(50), language VARCHAR(20), publishDate DATE, publisher VARCHAR(300),"
				+ "edition INTEGER, dimensions VARCHAR(100), weight VARCHAR(100), description VARCHAR(2000), editorReviews VARCHAR(2000), "
				+ "imgUrlS VARCHAR(500), imgUrlM VARCHAR(500), imgUrlL VARCHAR(500), amazonUrl VARCHAR(500), reviewUrl(500), averageRating REAL, ranking VARCHAR(100) )";

		logger.debug("Create table books: {}", sql);
		return stmt.execute(sql);

	};

	@Override
	protected boolean dropTable() throws Exception
	{
		String sql = "DROP TABLE books";
		return stmt.execute(sql);
	}
}
