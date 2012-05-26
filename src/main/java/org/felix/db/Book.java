package org.felix.db;

import java.sql.Date;

public class Book
{
	private final static int	MAX_LENGTH	= 200;
	/*
	 * primary id: book isbn (ISBN-10) Amazon book id (ASIN) = ISBN-10 Google book contains isbn (both isbn-10 and
	 * isbn-13) and a google book id
	 */
	private String	isbn;
	private String	isbn13;
	private String	googleId;
	private String	title;
	private String	subTitle;
	/* authors format: authorA|authorB|authorC */
	private String	authors;
	private int		pages;
	private String	price;
	private String	language;
	private Date	publishDate;
	private String	publisher;
	private int		edition;
	private String	dimensions;
	private String	weight;
	private String	description;
	private String	editorReviews;
	private String	imgUrlS;
	private String	imgUrlM;
	private String	imgUrlL;
	private String	amazonUrl;
	private String	reviewUrl;
	private float	averageRating;
	private String	ranking;

	public Book(String _isbn)
	{
		isbn = _isbn;
	}

	protected String replace(String old)
	{
		if (old == null) return null;
		// SQL String
		return old.replace("\'", "''");
	}

	private String subString(String str, int length)
	{
		String result = str;
		if (str.length() >= length) result = str.substring(0, (length - 4)) + "...";
		return result;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = replace(title);
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = replace(publisher);
	}

	public String getImgUrlS()
	{
		return imgUrlS;
	}

	public void setImgUrlS(String imgUrlS)
	{
		this.imgUrlS = replace(imgUrlS);
	}

	public String getImgUrlM()
	{
		return imgUrlM;
	}

	public void setImgUrlM(String imgUrlM)
	{
		this.imgUrlM = replace(imgUrlM);
	}

	public String getImgUrlL()
	{
		return imgUrlL;
	}

	public void setImgUrlL(String imgUrlL)
	{
		this.imgUrlL = replace(imgUrlL);
	}

	public String getAuthors()
	{
		return authors;
	}

	public void setAuthors(String authors)
	{
		this.authors = replace(authors);
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
		this.googleId = replace(googleId);
	}

	public String getSubTitle()
	{
		return subTitle;
	}

	public void setSubTitle(String subTitle)
	{
		this.subTitle = replace(subTitle);
	}

	public String getAmazonUrl()
	{
		return amazonUrl;
	}

	public void setAmazonUrl(String amazonUrl)
	{
		this.amazonUrl = replace(amazonUrl);
	}

	public String getReviewUrl()
	{
		return reviewUrl;
	}

	public void setReviewUrl(String reviewUrl)
	{
		this.reviewUrl = replace(reviewUrl);
	}

	public Date getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	public String getIsbn13()
	{
		return isbn13;
	}

	public void setIsbn13(String isbn13)
	{
		this.isbn13 = isbn13;
	}

	public int getEdition()
	{
		return edition;
	}

	public void setEdition(int edition)
	{
		this.edition = edition;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = subString(replace(description), MAX_LENGTH);
	}

	public String getEditorReviews()
	{
		return editorReviews;
	}

	public void setEditorReviews(String editorReviews)
	{
		this.editorReviews = subString(replace(editorReviews), MAX_LENGTH);
	}

	public int getPages()
	{
		return pages;
	}

	public void setPages(int pages)
	{
		this.pages = pages;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = replace(language);
	}

	public String getDimensions()
	{
		return dimensions;
	}

	public void setDimensions(String dimensions)
	{
		this.dimensions = replace(dimensions);
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = replace(weight);
	}

	public float getAverageRating()
	{
		return averageRating;
	}

	public void setAverageRating(float averageRating)
	{
		this.averageRating = averageRating;
	}

	public String getRanking()
	{
		return ranking;
	}

	public void setRanking(String ranking)
	{
		this.ranking = replace(ranking);
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = replace(price);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("ISBN_10: ").append(isbn).append("\n").append("ISBN_13: ").append(isbn13).append("\n")
				.append("Google ID: ").append(googleId).append("\n").append("Title: ").append(title).append("\n")
				.append("SubTitle: ").append(subTitle).append("\n").append("Authors: ").append(authors).append("\n")
				.append("Pages: ").append(pages).append("\n").append("Price: ").append(price).append("\n")
				.append("Language: ").append(language).append("\n").append("Publish Date: ").append(publishDate)
				.append("\n").append("Publisher: ").append(publisher).append("\n").append("Edition: ").append(edition)
				.append("\n").append("Dimensions: ").append(dimensions).append("\n").append("Weight: ").append(weight)
				.append("\n").append("Average Rating: ").append(averageRating).append("\n").append("Amazon Ranking: ")
				.append(ranking).append("\n").append("Image URL(S): ").append(imgUrlS).append("\n")
				.append("Image URL(M): ").append(imgUrlM).append("\n").append("Image URL(L): ").append(imgUrlL)
				.append("\n").append("Amazon URL: ").append(amazonUrl).append("\n").append("Review URL: ")
				.append(reviewUrl).append("\n").append("Editoral Reviews: \n").append(editorReviews).append("\n")
				.append("Description: \n").append(description).append("\n");

		return sb.toString();
	}
}
