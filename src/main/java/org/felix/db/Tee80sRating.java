package org.felix.db;

import java.sql.Date;

public class Tee80sRating extends DBObject
{
	private String	userId;
	private String	teeId;
	private float	rating;
	private String	comments	= "";
	private Date	rDate;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = replace(userId);
	}

	public String getTeeId()
	{
		return teeId;
	}

	public void setTeeId(String teeId)
	{
		this.teeId = replace(teeId);
	}

	public float getRating()
	{
		return rating;
	}

	public void setRating(float rating)
	{
		this.rating = rating;
	}

	public Date getrDate()
	{
		return rDate;
	}

	public void setrDate(Date rDate)
	{
		this.rDate = rDate;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = replace(comments);
	}
}
