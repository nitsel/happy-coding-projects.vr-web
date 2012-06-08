package org.felix.web.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.felix.util.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tee80sShirtClient extends DefaultWebClient
{
	public void getLinks() throws Exception
	{
		String source = "categories.txt";
		List<String> categories = FileUtils.readAsList(source);
		int max = 20;

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("tee80s-links.txt")));

		for (String c : categories)
		{
			logger.info("Current Progress: category = {}", c);
			for (int i = 1; i <= max; i++)
			{
				logger.info("Current Progress: page = {}", i);
				String html = super.query(new HttpGet(c + "&page=" + i));
				if (html == null) break;

				Document doc = Jsoup.parse(html);
				Elements es = doc.select("table.atomz_gs_results td[valign=top]");
				if (es == null || es.size() < 1) continue;

				Element e = null;
				Element el = null;
				for (int j = 0; j < es.size(); j++)
				{
					e = es.get(j);
					el = e.select("div.name a").first();
					String name = el.text();
					String link = el.attr("href");

					// only extract links for shirts
					if (name.contains("Shirt") || name.contains("shirt"))
					{
						el = e.select("div.price span").first();
						String price = el.text();

						Elements ee = e.select("script");
						if (ee == null || ee.size() < 1) break; // no more ratings existing

						el = ee.first();
						String data = el.data();
						data = data.substring(0, data.indexOf(';'));
						String rating = data.substring(data.indexOf('=') + 1, data.indexOf('*'));

						el = e.select("div.prSnippetNumberOfRatingsText").first();
						String totals = el.text();

						el = e.select("div.image img").first();
						String image = el.attr("src");

						String content = rating + "::" + totals + "::" + name + "::" + price + "::" + link + "::"
								+ image + "\n";
						bw.write(content);
						bw.flush();
					}

				}
			}
		}

		bw.close();
	}

	public static void main(String[] args) throws Exception
	{
		Tee80sShirtClient client = new Tee80sShirtClient();
		client.getLinks();
		client.filterRatings("tee80s-links.txt");
	}
}
