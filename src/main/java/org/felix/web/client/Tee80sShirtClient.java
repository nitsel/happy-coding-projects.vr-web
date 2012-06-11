package org.felix.web.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.felix.db.Tee80s;
import org.felix.db.Tee80sReview;
import org.felix.util.io.FileUtils;
import org.felix.util.system.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tee80sShirtClient extends DefaultWebClient
{
	/**
	 * parse html to obtain properties of tee80s
	 * 
	 * @param html
	 * @param t
	 * @throws Exception
	 */
	public void parseTee80s(String html, Tee80s t) throws Exception
	{
		Document doc = Jsoup.parse(html);

		Elements es = doc.select("meta[property]");
		Element e = null;
		for (int i = 0; i < es.size(); i++)
		{
			e = es.get(i);
			String item = e.attr("property");
			String value = e.attr("content");
			if ("og:title".equals(item))
				t.setName(value);
			else if ("og:type".equals(item))
				t.setType(value);
			else if ("og:url".equals(item))
				t.setUrl(value);
			else if ("og:description".equals(item))
				t.setDescription(value);
			else if ("og:image".equals(item))
				t.setImage(value);
			else if ("fb:admins".equals(item)) t.setAdmins(value);
		}

		es = doc.select("script");
		for (int i = 0; i < es.size(); i++)
		{
			e = es.get(i);
			String data = e.data();
			if (data == null || data.trim().isEmpty()) continue;

			if (data.contains("pr_locale"))
			{
				String[] items = data.split(";");
				for (String item : items)
				{
					if (item.contains("pr_locale"))
						t.setLocale(item.substring(item.indexOf('\'') + 1, item.lastIndexOf('\'')));
					else if (item.contains("pr_page_id"))
						t.setId(item.substring(item.indexOf('\'') + 1, item.lastIndexOf('\'')));
				}
			}
		}

		/* gender */
		e = doc.select("div#prodMainContent table td[width=32%]").first();
		t.setGender(e.text());

		/* image */
		e = doc.select("img[itemprop=image]").first();
		t.setImage(e.attr("src"));

		/* sizes */
		es = doc.select("label[for]");
		String sizes = "";
		for (int i = 0; i < es.size(); i++)
		{
			sizes += es.get(i).text();
			if (i < es.size() - 1) sizes += "::";
		}
		t.setSizes(sizes);

		/* price */
		String price = "";
		e = doc.select("meta[itemprop=priceCurrency]").first();
		price += e.attr("content");

		e = doc.select("span[itemprop=price]").first();
		price += e.text();
		t.setPrice(price);

		/* features */
		es = doc.select("ul[type=disc] li");
		String features = "";
		for (int i = 0; i < es.size(); i++)
		{
			e = es.get(i);
			features += e.text();
			if (i < es.size() - 1) features += "::";
		}
		t.setFeatures(features);

		/* rating */
		e = doc.select("span.pr-snippet-rating-decimal.pr-rounded").first();
		t.setAvgRating(Float.parseFloat(e.text()));

		e = doc.select("p.pr-snippet-review-count").first();
		String num = e.text();
		t.setNumRating(Integer.parseInt(num.substring(num.indexOf('(') + 1, num.indexOf(' '))));

	}
	
	/**
	 * parse html to obtain reviews
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public List<Tee80sReview> parseReview(String html) throws Exception
	{
		List<Tee80sReview> reviews = new ArrayList<Tee80sReview>();
		
		Document doc = Jsoup.parse(html);
		Elements es = null, els = null;
		Element e = null, el = null;
		
		es = doc.select("div.pr-review-wrap");
		for (int i = 0; i < es.size(); i++)
		{
			e = es.get(i);
			Tee80sReview review = new Tee80sReview();
			
			el = e.select("span.pr-rating.pr-rounded").first();
			review.setRating(Float.parseFloat(el.text()));
			review.setTitle(el.nextElementSibling().text());
			
			el = e.select("p.pr-review-author-name span").first();
			review.setUserName(el.text());
			
			el = e.select("p.pr-review-author-location span").first();
			review.setUserLocation(el.text());
			
			els = e.select("p.pr-review-author-affinities span");
			if (els != null && els.size() > 0) review.setTags(els.first().text());
			
			els = e.select("div.pr-attribute-group.pr-rounded.pr-attribute-pros li");
			String pros = "";
			if (els != null && els.size() > 0)
			{
				for (int j = 0; j < els.size(); j++)
				{
					pros += els.get(j).text();
					if (j < els.size() - 1) pros += "::";
				}
			}
			review.setPros(pros);
			
			els = e.select("div.pr-attribute-group.pr-rounded.pr-attribute-cons li");
			String cons = "";
			if (els != null && els.size() > 0)
			{
				for (int j = 0; j < els.size(); j++)
				{
					cons += els.get(j).text();
					if (j < els.size() - 1) cons += "::";
				}
			}
			review.setCons(cons);
			
			els = e.select("div.pr-attribute-group.pr-rounded.pr-attribute-bestuses.pr-last li");
			String bestUses = "";
			if (els != null && els.size() > 0)
			{
				for (int j = 0; j < els.size(); j++)
				{
					bestUses += els.get(j).text();
					if (j < els.size() - 1) bestUses += "::";
				}
			}
			review.setBestUses(bestUses);

			el = e.select("div.pr-review-text").first();
			review.setDetails(el.text());
			
			els = e.select("ul.pr-other-attributes-list li");
			for (int j = 0; j < els.size(); j += 2)
			{
				el = els.get(j);
				if (el.text().equalsIgnoreCase("Fit:"))
				{
					el = el.nextElementSibling();
					review.setFit(el.text());
				} else if (el.text().equalsIgnoreCase("Length:"))
				{
					el = el.nextElementSibling();
					review.setLength(el.text());
				} else if (el.text().equalsIgnoreCase("Was this a gift?:"))
				{
					el = el.nextElementSibling();
					review.setGift(el.text());
				}
			}
			
			els = e.select("div.pr-review-bottom-line-wrapper p");
			if (els != null && els.size() > 0)
			{
				String bl = els.first().text();
				review.setRecommendation(bl.substring(bl.indexOf("Bottom Line") + "Bottom Line ".length()));
			}
			
			el = e.select("div.pr-review-author-date.pr-rounded").first();
			review.setvDate(DateUtils.parseString(el.text(), DateUtils.PATTERN_MM_dd_yyyy));

			reviews.add(review);
		}

		return reviews;
	}

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
				String html = super.extractHtml(new HttpGet(c + "&page=" + i));
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
						if (ee == null || ee.size() < 1) break; // no more
																// ratings
																// existing

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

}
