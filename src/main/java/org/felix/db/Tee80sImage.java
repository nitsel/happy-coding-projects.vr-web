package org.felix.db;

import java.util.ArrayList;
import java.util.List;

import org.felix.util.io.FileUtils;
import org.felix.util.io.URLReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Tee80sImage
{
	public List<String> parseImages(String html) throws Exception
	{
		List<String> images = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements es = doc.select("div#imageBar td a");
		Element e = null;
		for (int i = 0; i < es.size() - 1; i++)
		{
			e = es.get(i);
			String image = e.attr("onclick");
			int start=image.indexOf('\'');
			image = image.substring(start + 1, image.indexOf('\'', start + 1));
			String[] data = image.split("&");
			for (String d : data)
			{
				if (d.startsWith("lgimage"))
				{
					image = d.substring(d.indexOf('=') + 1);
					break;
				}
			}
			// http://media.80stees.com/images/extraLarge/PEAN022_LG1.jpg
			String uri = "http://media.80stees.com/images/extraLarge/";
			image = uri + image;

			images.add(image);
		}
		
		return images;
	}

	public static void main(String[] args) throws Exception
	{
		List<String> links = FileUtils.readAsList("links.txt");
		Tee80sImage image = new Tee80sImage();

		for (String link : links)
		{
			String url = link.split("::")[4];
			String html = URLReader.read(url);

			List<String> data = image.parseImages(html);
			FileUtils.writeList("test.txt", data, true);
		}
	}
}
