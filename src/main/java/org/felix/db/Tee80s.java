package org.felix.db;

public class Tee80s
{
	private String	id;
	private String	name;
	private String	sizes;
	private String	price;
	private String	features;
	private String	gender;
	private String	type;
	private String	url;
	private String	description;
	private String	image;
	private String	admins;
	private String	locale;
	private float	avgRating;
	private int		numRating;

	@Override
	public String toString()
	{
		return " id = " + id + "\n name = " + name + "\n sizes = " + sizes + "\n price = " + price + "\n features = "
				+ features + "\n type = " + type + "\n url = " + url + "\n description = " + description
				+ "\n image = " + image + "\n admins = " + admins + "\n locale = " + locale + "\n avgRating = "
				+ avgRating + "\n numRating = " + numRating;
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
		this.name = name;
	}

	public String getSizes()
	{
		return sizes;
	}

	public void setSizes(String sizes)
	{
		this.sizes = sizes;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getFeatures()
	{
		return features;
	}

	public void setFeatures(String features)
	{
		this.features = features;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getAdmins()
	{
		return admins;
	}

	public void setAdmins(String admins)
	{
		this.admins = admins;
	}

	public String getLocale()
	{
		return locale;
	}

	public void setLocale(String locale)
	{
		this.locale = locale;
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
		this.gender = gender;
	}

}
