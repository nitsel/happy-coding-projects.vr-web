package org.felix.web.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultWebClient
{
	protected static Logger	logger	= LoggerFactory.getLogger(DefaultWebClient.class);

	/**
	 * extract html from some url using a GET request
	 * 
	 * @param get
	 * @return
	 * @throws Exception
	 */
	public String extractHtml(HttpGet get) throws Exception
	{
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();

		int status = response.getStatusLine().getStatusCode();
		if (entity != null && status == 200) return EntityUtils.toString(entity);

		return null;
	}

	public String extractHtml(HttpPost post) throws Exception
	{
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		int status = response.getStatusLine().getStatusCode();
		if (entity != null && status == 200) return EntityUtils.toString(entity);

		return null;
	}

	public void filterRatings(String filename) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File("rating-3.txt")));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File("rating-4.txt")));
		BufferedWriter bw3 = new BufferedWriter(new FileWriter(new File("rating-5.txt")));
		String line = null;
		while ((line = br.readLine()) != null)
		{
			String data[] = line.split("::");
			// String review = data[0];
			// int index = review.indexOf(" (");
			// if (index < 0) continue;
			// float rating = Float.parseFloat(review.substring(0, index));
			float rating = Float.parseFloat(data[0]);
			String num = data[1].substring(1, data[1].indexOf(' '));
			int total = Integer.parseInt(num);

			if (total < 4) continue;

			if (rating < 4.0) bw1.write(line + "\n");
			else if (rating < 4.5) bw2.write(line + "\n");
			else if (rating <= 5.0) bw3.write(line + "\n");
		}
		br.close();
		bw1.close();
		bw2.close();
		bw3.close();
	}
}
