package org.felix.db;


public class Book
{
	private String	ISBN;
	private String	title;
	/* authors format: authorA|authorB|authorC */
	private String	authors;
	private int		year;
	private String	publisher;
	private String	imgUrlS;
	private String	imgUrlM;
	private String	imgUrlL;

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

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}
}