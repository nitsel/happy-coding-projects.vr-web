package org.felix.web.client;

import java.io.Reader;
import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.felix.db.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleBookClient extends DefaultWebClient
{
	private static final Logger	logger	= LoggerFactory.getLogger(GoogleBookClient.class);

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

		return super.extractHtml(new HttpGet(url));
	}

	/**
	 * Search Google Books by using book's title and isbn
	 * 
	 * @param book
	 * @return JSON response codes; if no response, then return null
	 * @throws Exception
	 */
	public String searchBook(Book book) throws Exception
	{
		URIBuilder builder = new URIBuilder();

		builder.setScheme("https").setHost("www.googleapis.com").setPath("/books/v1/volumes")
				.setParameter("q", book.getTitle())
				.setParameter("isbn", "ISBN" + book.getIsbn());
		URI uri = builder.build();
		logger.info("Google book search url: {}", uri.toString());

		return super.extractHtml(new HttpGet(uri));
	}

	/**
	 * Search Google Book Using Title and ISBN
	 * 
	 * @param book
	 * @return Google Book ID
	 * @throws Exception
	 */
	public String searchBookId(Book book) throws Exception
	{
		String jsons = this.searchBook(book);
		if (jsons == null) return null;

		String googleId = this.parseBookJson(jsons, book.getIsbn());

		logger.info("Google Book Id = {}", googleId);

		return googleId;
	}

	public String parseBookJson(String jsons, String isbn) throws Exception
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsons);

		return parseJson(parser, obj, isbn);
	}

	public String parseBookJson(Reader in, String isbn) throws Exception
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(in);

		return parseJson(parser, obj, isbn);
	}

	@SuppressWarnings("unchecked")
	private String parseJson(JSONParser parser, Object object, String isbn)
	{
		JSONObject obj = (JSONObject) object;
		JSONArray items = (JSONArray) obj.get("items");
		if (items == null) return null;

		for (int i = 0; i < items.size(); i++)
		{
			JSONObject item = (JSONObject) items.get(i);
			JSONObject volumeInfo = (JSONObject) item.get("volumeInfo");
			List<Object> isbnArray = (List<Object>) volumeInfo.get("industryIdentifiers");
			if (isbnArray == null) continue;

			JSONObject isbnItem = (JSONObject) isbnArray.get(0);
			if (isbnItem.get("type").equals("ISBN_10"))
			{
				String id = isbnItem.get("identifier").toString();
				if (id.equals(isbn)) return item.get("id").toString();
			}

		}
		return null;
	}

}
