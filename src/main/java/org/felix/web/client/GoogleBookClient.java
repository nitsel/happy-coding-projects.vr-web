package org.felix.web.client;

import java.io.Reader;
import java.net.URI;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
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
		logger.info("Google book search url: {}", uri.toString());

		return super.query(new HttpGet(uri));
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
		String googleId = null;

		JSONObject obj = (JSONObject) object;
		JSONArray items = (JSONArray) obj.get("items");
		for (int i = 0; i < items.size(); i++)
		{
			JSONObject item = (JSONObject) items.get(i);
			JSONObject volumeInfo = (JSONObject) item.get("volumeInfo");
			List<Object> isbnArray = (List<Object>) volumeInfo.get("industryIdentifiers");

			JSONObject isbnItem = (JSONObject) isbnArray.get(0);
			if (isbnItem.get("type").equals("ISBN_10"))
			{
				String id = isbnItem.get("identifier").toString();
				if (id.equals(isbn)) return item.get("id").toString();
			}

		}
		return googleId;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String title = "A Second Chicken Soup for the Woman's";
		// String subTitle = "Soul Chicken Soup for the Soul Series";
		String isbn = "1558746226";
		GoogleBookClient book = new GoogleBookClient();
		String jsons = book.searchBook(title, isbn);

		// String file = "googleBookResponse.json";
		// InputStream in = GoogleBook.class.getClassLoader().getResourceAsStream(file);
		// String googleId = book.parseBookJson(new InputStreamReader(in), isbn);

		String googleId = book.parseBookJson(jsons, isbn);
		logger.info("Google Book Id = {}", googleId);
	}

}
