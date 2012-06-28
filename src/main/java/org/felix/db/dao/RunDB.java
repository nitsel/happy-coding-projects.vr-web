package org.felix.db.dao;

import java.util.List;

import org.felix.db.Book;
import org.felix.web.client.AmazonBookClient;
import org.felix.web.client.GoogleBookClient;
import org.felix.web.ws.AmazonBookLookup;

public class RunDB
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		GoogleBookClient googleBook = new GoogleBookClient();
		AmazonBookClient amazonBook = new AmazonBookClient();
		int count = 0, max = 100;

		BookDao dao = new BookDao();
		List<String> isbns = dao.retrieveAllBooks();
		if (isbns == null || isbns.size() == 0) return;

		for (String isbn : isbns)
		{
			Book book = dao.query(isbn);

			/* Search if Google Books and Amazon Web Services have such previews or records */
			if (book.getAmazonUrl().isEmpty())
			{
				String googleId = googleBook.searchBookId(book);
				book.setGoogleId(googleId);

				AmazonBookLookup.retrieveBook(book);
				dao.update(book);

				// control the search rate: too fast will result in error response
				Thread.sleep(5000);
			}

			/* Search the book's details from Amazon web site */
			if (book.getDescription().isEmpty())
			{
				amazonBook.searchBook(book);
				dao.update(book);

				/* update to the database */
				if (++count > max) break;
			}
		}

	}

}
