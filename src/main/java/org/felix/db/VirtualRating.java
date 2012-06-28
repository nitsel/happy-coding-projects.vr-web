package org.felix.db;

import java.sql.Date;

public class VirtualRating extends DBObject
{
	private String	userId;
	private String	teeId;
	private String	comments	= "";
	private Date	cDate;
	private String	environment;		// web site or virtual reality

	/* specific ratings */
	private int		overall;
	private int		appearance;
	private int		material;
	private int		fit;
	private int		category;
	private int		price;
	private int		brand;
	private int		store;
	private int		shipping;
	private int		quality;
	private int		cost;
	private int		value;

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

	public Date getcDate()
	{
		return cDate;
	}

	public void setcDate(Date cDate)
	{
		this.cDate = cDate;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = replace(comments);
	}

	public String getEnvironment()
	{
		return environment;
	}

	public void setEnvironment(String environment)
	{
		this.environment = replace(environment);
	}

	public int getOverall()
	{
		return overall;
	}

	public void setOverall(int overall)
	{
		this.overall = overall;
	}

	public int getAppearance()
	{
		return appearance;
	}

	public void setAppearance(int appearance)
	{
		this.appearance = appearance;
	}

	public int getMaterial()
	{
		return material;
	}

	public void setMaterial(int material)
	{
		this.material = material;
	}

	public int getFit()
	{
		return fit;
	}

	public void setFit(int fit)
	{
		this.fit = fit;
	}

	public int getCategory()
	{
		return category;
	}

	public void setCategory(int category)
	{
		this.category = category;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getBrand()
	{
		return brand;
	}

	public void setBrand(int brand)
	{
		this.brand = brand;
	}

	public int getStore()
	{
		return store;
	}

	public void setStore(int store)
	{
		this.store = store;
	}

	public int getShipping()
	{
		return shipping;
	}

	public void setShipping(int shipping)
	{
		this.shipping = shipping;
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}

	public int getCost()
	{
		return cost;
	}

	public void setCost(int cost)
	{
		this.cost = cost;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
