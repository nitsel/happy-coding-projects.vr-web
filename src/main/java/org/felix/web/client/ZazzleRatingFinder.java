package org.felix.web.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ZazzleRatingFinder extends DefaultWebClient
{

	public void findRatings() throws Exception
	{
		String pageUrl = "http://www.zazzle.com/mens+tshirts?st=price&sd=asc";
		int page = 0;

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("results.txt"), true));

		while (page < 10)
		{
			page++;
			if (page > 1) pageUrl += "&pg=" + page;

			String html = super.query(new HttpGet(pageUrl));
			if (html == null) continue;

			// retrieve t-shirts urls from html
			List<String> urls = retrieveUrls(html);

			// check each t-shirt
			for (String url : urls)
			{
				String content = super.query(new HttpGet(url));

				Document doc = Jsoup.parse(content);
				Elements es = doc.select("#page_overallRatingCart-detailLabel");
				if (es != null && es.size() > 0)
				{
					String text = es.first().text();
					int index = text.indexOf('(');
					if (index < 0) continue;
					// float rating = Float.parseFloat(text.substring(0, index));

					String result = text + "|" + url;
					bw.write(result + "\n");
				}

			}
		}

		bw.close();

	}

	private List<String> retrieveUrls(String html)
	{
		List<String> urls = new ArrayList<String>();

		Document doc = Jsoup.parse(html);
		Elements es = null;

		String selector = "a#page_productsSearch_collection_cell";
		for (int i = 0; i < 60; i++)
		{
			String s = selector + i + "-imageLink";

			es = doc.select(s);
			if (es != null && es.size() > 0)
			{
				String url = es.first().attr("href");
				urls.add(url);
			}
		}

		return urls;

	}

	public static void main(String[] args) throws Exception
	{
		new ZazzleRatingFinder().findRatings();
	}

}
