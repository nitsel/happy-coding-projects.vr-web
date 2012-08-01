package org.felix.db;

import java.sql.Date;

public class PilotStudy extends DBObject
{
	private int		userId;
	private int		appearance;
	private int		material;
	private int		fit;
	private int		situation;
	private int		customization;
	private int		rating;
	private int		brand;
	private int		store;
	private int		recommendation;
	private int		category;
	private int		warranty;
	private int		price;
	private int		promotion;
	private int		shipping;
	private String	otherFeature	= "";
	private int		others;
	private String	comments		= "";
	private Date	cDate;

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

	public int getSituation()
	{
		return situation;
	}

	public void setSituation(int situation)
	{
		this.situation = situation;
	}

	public int getCustomization()
	{
		return customization;
	}

	public void setCustomization(int customization)
	{
		this.customization = customization;
	}

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
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

	public int getRecommendation()
	{
		return recommendation;
	}

	public void setRecommendation(int recommendation)
	{
		this.recommendation = recommendation;
	}

	public int getCategory()
	{
		return category;
	}

	public void setCategory(int category)
	{
		this.category = category;
	}

	public int getWarranty()
	{
		return warranty;
	}

	public void setWarranty(int warranty)
	{
		this.warranty = warranty;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getPromotion()
	{
		return promotion;
	}

	public void setPromotion(int promotion)
	{
		this.promotion = promotion;
	}

	public int getShipping()
	{
		return shipping;
	}

	public void setShipping(int shipping)
	{
		this.shipping = shipping;
	}

	public int getOthers()
	{
		return others;
	}

	public void setOthers(int others)
	{
		this.others = others;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = replace(comments);
	}

	public Date getcDate()
	{
		return cDate;
	}

	public void setcDate(Date cDate)
	{
		this.cDate = cDate;
	}

	public String getOtherFeature()
	{
		return otherFeature;
	}

	public void setOtherFeature(String otherFeature)
	{
		this.otherFeature = replace(otherFeature);
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

}
