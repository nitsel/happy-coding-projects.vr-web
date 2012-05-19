package org.felix.phd.web;

import java.sql.Date;
import java.util.List;

public class Book
{
	private String			ISBN;
	private String			title;
	private List<String>	authors;
	private Date			publishDate;
	private String			publisher;
	private String			imgUrlS;
	private String			imgUrlM;
	private String			imgUrlL;

	public String getISBN()
	{
		return ISBN;
	}

	public void setISBN(String iSBN)
	{
		ISBN = iSBN;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public List<String> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<String> authors)
	{
		this.authors = authors;
	}

	public Date getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getImgUrlS()
	{
		return imgUrlS;
	}

	public void setImgUrlS(String imgUrlS)
	{
		this.imgUrlS = imgUrlS;
	}

	public String getImgUrlM()
	{
		return imgUrlM;
	}

	public void setImgUrlM(String imgUrlM)
	{
		this.imgUrlM = imgUrlM;
	}

	public String getImgUrlL()
	{
		return imgUrlL;
	}

	public void setImgUrlL(String imgUrlL)
	{
		this.imgUrlL = imgUrlL;
	}
}
