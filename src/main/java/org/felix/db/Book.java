package org.felix.db;

public class Book
{
	/*
	 * primary id: book isbn (ISBN-10) Amazon book id (ASIN) = ISBN-10 Google
	 * book contains isbn (both isbn-10 and isbn-13) and a google book id
	 */
	private String isbn;
	private String googleId;
	private String title;
	private String subTitle;
	/* authors format: authorA|authorB|authorC */
	private String authors;
	private int publishYear;
	private String publisher;
	private String manufacturer;
	private String imgUrlS;
	private String imgUrlM;
	private String imgUrlL;
	private String amazonUrl;

	public Book(String _isbn)
	{
		isbn = _isbn;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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

	public String getAuthors()
	{
		return authors;
	}

	public void setAuthors(String authors)
	{
		this.authors = authors;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	public String getGoogleId()
	{
		return googleId;
	}

	public void setGoogleId(String googleId)
	{
		this.googleId = googleId;
	}

	public String getSubTitle()
	{
		return subTitle;
	}

	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	
	public int getPublishYear()
	{
		return publishYear;
	}
	
	public void setPublishYear(int publishYear)
	{
		this.publishYear = publishYear;
	}
	
	public String getAmazonUrl()
	{
		return amazonUrl;
	}
	
	public void setAmazonUrl(String amazonUrl)
	{
		this.amazonUrl = amazonUrl;
	}
}
