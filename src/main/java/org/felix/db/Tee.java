package org.felix.db;

public class Tee extends DBObject
{
	private String	id;
	private String	name			= "";
	private String	category		= "";
	private String	sizes			= "";
	private String	price			= "";
	private String	features		= "";
	private String	gender			= "";
	private String	type			= "";
	private String	url				= "";
	private String	description		= "";
	private String	image			= "";
	private String	admins			= "";
	private String	locale			= "";

	/* info about overall rating and review */
	private float	avgRating;
	private int		numRating;
	private String	pros			= "";
	private String	cons			= "";
	private String	bestUses		= "";
	private String	reviewerProfile	= "";
	private String	gift			= "";
	private String	recommendation	= "";

	@Override
	public String toString()
	{
		return " id = " + id + "\n name = " + name + "\n sizes = " + sizes + "\n price = " + price + "\n features = "
				+ features + "\n gender = " + gender + "\n type = " + type + "\n url = " + url + "\n description = "
				+ description + "\n image = " + image + "\n admins = " + admins + "\n locale = " + locale
				+ "\n avgRating = " + avgRating + "\n numRating = " + numRating + "\n recommendation = "
				+ recommendation + "\n pros = " + pros + "\n cons = " + cons + "\n bestUses = " + bestUses
				+ "\n reviewerProfile = " + reviewerProfile + "\n gift = " + gift;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = replace(name);
	}

	public String getSizes()
	{
		return sizes;
	}

	public void setSizes(String sizes)
	{
		this.sizes = replace(sizes);
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = replace(price);
	}

	public String getFeatures()
	{
		return features;
	}

	public void setFeatures(String features)
	{
		this.features = replace(features);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = replace(type);
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = replace(url);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = replace(description);
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = replace(image);
	}

	public String getAdmins()
	{
		return admins;
	}

	public void setAdmins(String admins)
	{
		this.admins = replace(admins);
	}

	public String getLocale()
	{
		return locale;
	}

	public void setLocale(String locale)
	{
		this.locale = replace(locale);
	}

	public float getAvgRating()
	{
		return avgRating;
	}

	public void setAvgRating(float avgRating)
	{
		this.avgRating = avgRating;
	}

	public int getNumRating()
	{
		return numRating;
	}

	public void setNumRating(int numRating)
	{
		this.numRating = numRating;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = replace(gender);
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

	public String getReviewerProfile()
	{
		return reviewerProfile;
	}

	public void setReviewerProfile(String reviewerProfile)
	{
		this.reviewerProfile = replace(reviewerProfile);
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

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = replace(category);
	}

}
