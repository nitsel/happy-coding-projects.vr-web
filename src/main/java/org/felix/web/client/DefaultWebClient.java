package org.felix.web.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultWebClient
{
	Logger	logger	= LoggerFactory.getLogger(DefaultWebClient.class);

	public String query(HttpGet get) throws Exception
	{
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();

		int status = response.getStatusLine().getStatusCode();
		if (entity != null && status == 200) return EntityUtils.toString(entity);

		return null;
	}
}
