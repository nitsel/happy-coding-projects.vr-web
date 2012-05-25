package org.felix.db;

import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;

import au.com.bytecode.opencsv.CSVReader;

public class BookDao extends Dao
{
	private final static String	tableElements	= "isbn, isbn13, googleId, title, subTitle, authors, "
			+ "pages, price, language, publishDate, publisher, edition, dimensions, weight, description, "
			+ "editorReviews, imgUrlS, imgUrlM, imgUrlL, amazonUrl, reviewUrl, averageRating, ranking";

	void update(Book book) throws Exception
	{
		String sql = "UPDATE books SET isbn13='" + book.getIsbn13() + "', googleId='" + book.getGoogleId() + "'"
		+"', title='"+book.getTitle()+"', subTitle = '"+book.getSubTitle()+"', authors='"+book.getAuthors()
		+"', pages=" +book.getPages()+", price = '"+book.getPrice()+"', language ='"+book.getLanguage()
		+"', publishDate='"+book.getPublishDate()+"', publisher ='"+book.getPublisher()+"', edition="+book.getEdition()
		+", dimensions='"+book.getDimensions()+"', weight='"+book.getWeight()+"', description = '"+book.getDescription()
		+"', editorReviews='"+book.getEditorReviews()+"', imgUrlS='"+book.getImgUrlS()+"', imgUrlM='"+book.getImgUrlM()
		+"', imgUrlL='"+book.getImgUrlL()+"', averageRating ="+book.getAverageRating()+", ranking='"+book.getRanking()
		+ "' WHERE isbn='" + book.getIsbn() + "'";
		
		logger.debug("Update book: {}", sql);
		stmt.executeUpdate(sql);
	};

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
				+ (book.getIsbn13() == null ? "" : book.getIsbn13()) + "', '"
				+ (book.getGoogleId() == null ? "" : book.getGoogleId()) + "', '"
				+ (book.getTitle() == null ? "" : book.getTitle()) + "', '"
				+ (book.getSubTitle() == null ? "" : book.getSubTitle()) + "', '"
				+ (book.getAuthors() == null ? "" : book.getAuthors()) + "', " + book.getPages() + ", '"
				+ (book.getPrice() == null ? "" : book.getPrice()) + "', '"
				+ (book.getLanguage() == null ? "" : book.getLanguage()) + "', '"
				+ (book.getPublishDate() == null ? "" : book.getPublishDate()) + "', '"
				+ (book.getPublisher() == null ? "" : book.getPublisher()) + "', " + book.getEdition() + ", '"
				+ (book.getDimensions() == null ? "" : book.getDimensions()) + "', '"
				+ (book.getWeight() == null ? "" : book.getWeight()) + "', '"
				+ (book.getDescription() == null ? "" : book.getDescription()) + "', '"
				+ (book.getEditorReviews() == null ? "" : book.getEditorReviews()) + "', '"
				+ (book.getImgUrlS() == null ? "" : book.getImgUrlS()) + "', '"
				+ (book.getImgUrlM() == null ? "" : book.getImgUrlM()) + "', '"
				+ (book.getImgUrlL() == null ? "" : book.getImgUrlL()) + "', '"
				+ (book.getAmazonUrl() == null ? "" : book.getAmazonUrl()) + "', '"
				+ (book.getReviewUrl() == null ? "" : book.getReviewUrl()) + "', " + book.getAverageRating() + ", '"
				+ (book.getRanking() == null ? "" : book.getRanking()) + "')";

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
	protected boolean createTable() throws Exception
	{

		String sql = "CREATE TABLE books (isbn VARCHAR(20) PRIMARY KEY, isbn13 VARCHAR(20), googleId VARCHAR(30), title VARCHAR(500) NOT NULL,"
				+ "subTitle VARCHAR(500), authors VARCHAR(200), pages INTEGER, price VARCHAR(50), language VARCHAR(20), publishDate DATE, publisher VARCHAR(300),"
				+ "edition INTEGER, dimensions VARCHAR(100), weight VARCHAR(100), description VARCHAR(2000), editorReviews VARCHAR(2000), "
				+ "imgUrlS VARCHAR(500), imgUrlM VARCHAR(500), imgUrlL VARCHAR(500), amazonUrl VARCHAR(500), reviewUrl VARCHAR(500), averageRating REAL, ranking VARCHAR(100) )";

		logger.debug("Create table books: {}", sql);
		return stmt.execute(sql);

	};

	@Override
	protected boolean dropTable() throws Exception
	{
		String sql = "DROP TABLE books";
		return stmt.execute(sql);
	}
	
	protected void initialTable() throws Exception
	{
		CSVReader br = new CSVReader(new FileReader(new File("Book-Crossing/BX-Books.csv")), ';', '"', 1);
		String[] data = null;
		int count = 0;
		int max = 10;
		while ((data = br.readNext()) != null)
		{
			String isbn = data[0];
			String title = replace(data[1]);
			String authors = replace(data[2]);
			String date = "Jan 1, " + replace(data[3]);
			
			String publisher = replace(data[4]);
			String imgUrlS = replace(data[5]);
			String imgUrlM = replace(data[6]);
			String imgUrlL = replace(data[7]);
			
			Book book = new Book(isbn);
			book.setTitle(title);
			book.setAuthors(authors);
			book.setPublisher(publisher);
			book.setImgUrlS(imgUrlS);
			book.setImgUrlM(imgUrlM);
			book.setImgUrlL(imgUrlL);
			
			DateFormat format = DateFormat.getDateInstance(DateFormat.LONG);
			book.setPublishDate(new Date(format.parse(date).getTime()));

			insert(book);
			if (++count > max) break;
		}
		
		logger.debug("Initial table books successfully!");
		br.close();
	}

	public static void main(String[] args) throws Exception
	{
		new BookDao().initialTable();
	}
}
