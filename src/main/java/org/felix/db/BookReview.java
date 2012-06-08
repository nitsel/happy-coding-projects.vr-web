package org.felix.db;

import java.sql.Date;

public class BookReview extends Product
{
	private String	userId;
	private String	userName;
	private String	userProfileUrl;
	private String	isbn;			// book id
	private float	rating;
	private String	title;
	private String	content;
	private Date	publishDate;

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("userId = ").append(userId).append("\n")
		  .append("userName = ").append(userName).append("\n")
		  .append("userProfileUrl = ").append(userProfileUrl).append("\n")
		  .append("isbn = ").append(isbn).append("\n")
		  .append("rating = ").append(rating).append("\n")
		  .append("title = ").append(title).append("\n")
		  .append("publishDate = ").append(publishDate).append("\n")
		  .append("content = ").append(content).append("\n");

		return sb.toString();
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getIsbn()
	{
		return isbn;
	}

	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getPublishDate()
	{
		return publishDate;
	}

	public void setPublishDate(Date publishDate)
	{
		this.publishDate = publishDate;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public float getRating()
	{
		return rating;
	}

	public void setRating(float rating)
	{
		this.rating = rating;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserProfileUrl()
	{
		return userProfileUrl;
	}

	public void setUserProfileUrl(String userProfileUrl)
	{
		this.userProfileUrl = userProfileUrl;
	}
}
