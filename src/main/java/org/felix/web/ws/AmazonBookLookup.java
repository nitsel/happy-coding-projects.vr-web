/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package org.felix.web.ws;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.felix.db.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
public class AmazonBookLookup
{
	private static final Logger logger = LoggerFactory.getLogger(AmazonBookLookup.class);
	/*
	 * Your AWS Access Key ID, as taken from the AWS Your Account page.
	 */
	private static final String AWS_ACCESS_KEY_ID = "AKIAIWHTQU7L3LARWXKA";
	
	/*
	 * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
	 * Your Account page.
	 */
	private static final String AWS_SECRET_KEY = "AtEKLaQYOhW+I+1uf+2o+k7cXtCFgWReSr68ilkA";
	private static final String ASSOCIATE_TAG = "vrweb-20";
	
	/*
	 * Use one of the following end-points, according to the region you are
	 * interested in:
	 * 
	 * US: ecs.amazonaws.com CA: ecs.amazonaws.ca UK: ecs.amazonaws.co.uk DE:
	 * ecs.amazonaws.de FR: ecs.amazonaws.fr JP: ecs.amazonaws.jp
	 */
	private static final String ENDPOINT = "ecs.amazonaws.com";

	/**
	 * Retrieve Book Information from Amazon Web Service
	 * 
	 * @param book
	 * @throws Exception
	 */
	public static void retrieveBook(Book book) throws Exception
	{ 
		/*
		 * Set up the signed requests helper
		 */
		SignedRequestsHelper helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		/*
		 * Here is an example in map form, where the request parameters are
		 * stored in a map.
		 * 
		 * ResponseGroup:
		 * http://docs.amazonwebservices.com/AWSECommerceService/latest
		 * /DG/CHAP_ResponseGroupsList.html
		 */
		Map<String, String> params = new HashMap<>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", date);
		params.put("Operation", "ItemLookup");
		params.put("AssociateTag", ASSOCIATE_TAG);
		params.put("ItemId", book.getIsbn());
		params.put("ResponseGroup", "Small");
		
		String requestUrl = helper.sign(params);
		logger.info("Signed Request is \"" + requestUrl + "\"");
		
		fetchBook(book, requestUrl);

	}
	
	/*
	 * Utility function to fetch the response from the service and extract the
	 * title from the XML.
	 */
	private static void fetchBook(Book book, String requestUrl) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(requestUrl);
		
		Node urlNode = doc.getElementsByTagName("DetailPageURL").item(0);
		String amazonUrl = urlNode.getTextContent();
		book.setAmazonUrl(amazonUrl);
		
		NodeList itemLinkList = doc.getElementsByTagName("ItemLink");
		for (int i = 0; i < itemLinkList.getLength(); i++)
		{
			Node itemLinkNode = itemLinkList.item(i);
			Node desNode = itemLinkNode.getFirstChild();
			if (desNode.getTextContent().equals("All Customer Reviews"))
			{
				Node reviewNode = itemLinkNode.getLastChild();
				String reviewUrl = reviewNode.getTextContent();
				book.setReviewUrl(reviewUrl);
				break;
			}
		}

		NodeList authorList = doc.getElementsByTagName("Author");
		String authors = null;
		for (int i = 0; i < authorList.getLength(); i++)
		{
			Node authorNode = authorList.item(i);
			if (i == 0) authors = authorNode.getTextContent();
			else
				authors += "|" + authorNode.getTextContent();
		}
		if (authors != null) book.setAuthors(authors);

		Node manuNode = doc.getElementsByTagName("Manufacturer").item(0);
		String publisher = manuNode.getTextContent();
		book.setPublisher(publisher);

		Node titleNode = doc.getElementsByTagName("Title").item(0);
		String title = titleNode.getTextContent();
		book.setTitle(title);
	}

}
