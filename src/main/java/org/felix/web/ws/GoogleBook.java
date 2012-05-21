package org.felix.web.ws;

import java.io.File;
import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class GoogleBook extends DefaultHttpImpl
{

	/**
	 * Search Google Books by using isbn
	 * 
	 * @param isbn
	 * @return content of a web page
	 * @throws Exception
	 */
	public String searchBook(String isbn) throws Exception
	{
		String url = "http://books.google.com.sg/books?vid=ISBN" + isbn;

		return super.query(new HttpGet(url));
	}

	/**
	 * Search Google Books by using title and isbn
	 * 
	 * @param title
	 * @param isbn
	 * @return JSON response codes
	 * @throws Exception
	 */
	public String searchBook(String title, String isbn) throws Exception
	{
		URIBuilder builder = new URIBuilder();
		builder.setScheme("https").setHost("www.googleapis.com").setPath("/books/v1/volumes").setParameter("q", title)
				.setParameter("isbn", "ISBN" + isbn);
		URI uri = builder.build();

		return super.query(new HttpGet(uri));
	}

	public void parseBookJson(String jsons)
	{

	}

	public void parseBookJson(File jsonf)
	{
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String title = "A Second Chicken Soup for the Woman's Soul (Chicken Soup for the Soul Series)";
		String isbn = "1558746226";
		GoogleBook book = new GoogleBook();
		String content1 = book.searchBook(title, isbn);
		String content2 = book.searchBook(isbn);

		File file = new File("googleBookResponse.json");
		book.parseBookJson(file);
	}

}
