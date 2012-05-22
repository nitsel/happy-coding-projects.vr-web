package org.felix.web.client;

import org.apache.http.client.methods.HttpGet;
import org.felix.db.Book;
import org.felix.web.ws.AmazonBookLookup;

/* Load the information of the book from Amazon site */

public class AmazonBookClient extends DefaultWebClient
{
	public Book searchBook(String isbn) throws Exception
	{
		Book book = AmazonBookLookup.retrieveBook(isbn);
		
		String amazonUrl = book.getAmazonUrl();
		String htmlContent = super.query(new HttpGet(amazonUrl));
		

		/* parse html code to obtain book information */
		// TODO: adding parsing codes here

		return book;
	}
	
	public static void main(String[] args) throws Exception
	{
		AmazonBookClient amazon = new AmazonBookClient();
		String isbn = "1558746226";
		amazon.searchBook(isbn);
	}
}
