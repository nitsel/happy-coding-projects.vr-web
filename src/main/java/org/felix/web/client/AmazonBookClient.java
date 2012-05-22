package org.felix.web.client;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.felix.db.Book;
import org.felix.db.Review;
import org.felix.web.ws.AmazonBookLookup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* Load the information of the book from Amazon site */

public class AmazonBookClient extends DefaultWebClient
{
	public Book searchBook(String isbn) throws Exception
	{
		Book book = AmazonBookLookup.retrieveBook(isbn);
		String amazonUrl = book.getAmazonUrl();
		String html = super.query(new HttpGet(amazonUrl));

		// Book book = new Book(isbn);
		// String html = FileOperUtil.read("amazonBook.html");

		Document doc = Jsoup.parse(html);
		Elements es = null;
		Element e = null;
		String item = null, content = null;

		/* Section: Book Info */
		e = doc.select("div#averageCustomerReviewStars span").first();
		content = e.attr("title");
		content = content.substring(0, content.indexOf(' '));
		book.setAverageRating(Float.parseFloat(content));

		e = doc.select("span#actualPriceValue b").first();
		content = e.ownText();
		book.setPrice(content);

		/* Section: Book Description */
		es = doc.select("div#ps-content .buying span");

		String previous = null, current = null;
		for (int i = 0; i < es.size(); i++)
		{
			Element t = es.get(i);
			current = t.ownText();
			if (previous != null && previous.equals("Publication Date:"))
			{
				/* format String "October 1, 1998" to java.sql.Date */
				DateFormat format = DateFormat.getDateInstance(DateFormat.LONG);
				book.setPublishDate(new Date(format.parse(current).getTime()));
			} else if (previous != null && previous.equals("ISBN-13:"))
			{
				book.setIsbn13(current);
			} else if (previous != null && previous.equals("Edition:"))
			{
				book.setEdition(Integer.parseInt(current));
			}

			previous = current;
		}

		e = doc.select("div#postBodyPS div").get(0);
		book.setDescription(e.html());

		/* Section: Editorial Reviews */
		e = doc.select("div#productDescription .content").get(0);
		book.setEditorReviews(e.html());

		/* Section: Product Details */
		es = doc.select("table td.bucket div.content li");
		for (int i = 0; i < es.size(); i++)
		{
			e = es.get(i);
			Element bold = e.getElementsByTag("b").first();
			item = bold.ownText().trim();
			content = e.ownText().trim();

			if (item.equals("Paperback:"))
			{
				int pages = Integer.parseInt(content.substring(0, content.indexOf(' ')));
				book.setPages(pages);
			} else if (item.equals("Language:"))
			{
				book.setLanguage(content);
			} else if (item.equals("ISBN-13:"))
			{
				book.setIsbn13(content);
			} else if (item.equals("Product Dimensions:"))
			{
				book.setDimensions(content);
			} else if (item.equals("Shipping Weight:"))
			{
				content = content.substring(0, content.indexOf('('));
				book.setWeight(content);
			} else if (item.equals("Amazon Best Sellers Rank:"))
			{
				content = content.substring(0, content.indexOf('('));
				book.setRanking(content);
			}
		}

		return book;
	}

	public List<Review> searchReviews(String reviewUrl) throws Exception
	{
		int max = 20; // 20 reviews for each product is good enough
		List<Review> reviews = new ArrayList<Review>();
		// TODO: adding codes to retrieve reviews from amazon

		return reviews;
	}

	public static void main(String[] args) throws Exception
	{
		AmazonBookClient amazon = new AmazonBookClient();
		String isbn = "1558746226";
		Book book = amazon.searchBook(isbn);
		System.out.println(book);
	}
}
