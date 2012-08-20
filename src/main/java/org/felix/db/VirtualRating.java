package org.felix.db;

import java.sql.Date;

public class VirtualRating extends DBObject
{
	private int		userId;
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

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append(userId + sep);
		sb.append(teeId + sep);
		sb.append(overall + sep);
		sb.append(environment + sep);
		sb.append(appearance + sep);
		sb.append(material + sep);
		sb.append(fit + sep);
		sb.append(category + sep);
		sb.append(price + sep);
		sb.append(brand + sep);
		sb.append(store + sep);
		sb.append(shipping + sep);
		sb.append(quality + sep);
		sb.append(cost + sep);
		sb.append(value + sep);
		sb.append(comments + sep);
		sb.append(cDate);

		return sb.toString();
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
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
