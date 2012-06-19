package org.felix.db;

import java.sql.Date;

public class Tee80sReview extends DBObject
{
	private int id;
	private float rating;
	private String	productId		= "";

	private String	userName		= "";
	private String	userLocation	= "";
	private String	tags			= "";
	private String	title			= "";
	private String	details			= "";
	private String	pros			= "";
	private String	cons			= "";
	private String	bestUses		= "";
	private String	fit				= "";
	private String	length			= "";
	private String	gift			= "";
	private String	recommendation	= "";
	private Date vDate;
	
	@Override
	public String toString()
	{
		return " id = " + id + "\n product id = " + productId + "\n rating = " + rating + "\n userName = " + userName
				+ "\n userLocation = " + userLocation + "\n tags = " + tags + "\n title = " + title + "\n pros = "
				+ pros + "\n cons = " + cons + "\n best uses = " + bestUses + "\n details = " + details + "\n fit = "
				+ fit + "\n length = " + length + "\n gift = " + gift + "\n recommendation = " + recommendation
				+ "\n vDate = " + vDate;
	}

	public String getProductId()
	{
		return productId;
	}
	
	public void setProductId(String productId)
	{
		this.productId = productId;
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
		this.userName = replace(userName);
	}
	
	public String getUserLocation()
	{
		return userLocation;
	}
	
	public void setUserLocation(String userLocation)
	{
		this.userLocation = replace(userLocation);
	}
	
	public String getTags()
	{
		return tags;
	}
	
	public void setTags(String tags)
	{
		this.tags = replace(tags);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = replace(title);
	}
	
	public String getDetails()
	{
		return details;
	}
	
	public void setDetails(String details)
	{
		this.details = replace(details);
	}
	
	public String getPros()
	{
		return pros;
	}
	
	public void setPros(String pros)
	{
		this.pros = replace(pros);
	}
	
	public String getCons()
	{
		return cons;
	}
	
	public void setCons(String cons)
	{
		this.cons = replace(cons);
	}
	
	public String getBestUses()
	{
		return bestUses;
	}
	
	public void setBestUses(String bestUses)
	{
		this.bestUses = replace(bestUses);
	}
	
	public String getFit()
	{
		return fit;
	}
	
	public void setFit(String fit)
	{
		this.fit = replace(fit);
	}
	
	public String getLength()
	{
		return length;
	}
	
	public void setLength(String length)
	{
		this.length = replace(length);
	}
	
	public String getGift()
	{
		return gift;
	}
	
	public void setGift(String gift)
	{
		this.gift = replace(gift);
	}
	
	public String getRecommendation()
	{
		return recommendation;
	}
	
	public void setRecommendation(String recommendation)
	{
		this.recommendation = replace(recommendation);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Date getvDate()
	{
		return vDate;
	}
	
	public void setvDate(Date vDate)
	{
		this.vDate = vDate;
	}
}
