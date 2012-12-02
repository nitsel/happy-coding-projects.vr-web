package org.felix.db;

public class Will
{
	private int id;
	private String willingness ;
	private String yesReasons;
	private String noReasons;
	private String confidence;
	private String conditions;
	
	private String userId;

	public String getWillingness()
	{
		return willingness;
	}

	public void setWillingness(String willingness)
	{
		this.willingness = willingness;
	}

	public String getYesReasons()
	{
		return yesReasons;
	}

	public void setYesReasons(String yesReasons)
	{
		this.yesReasons = yesReasons;
	}

	public String getNoReasons()
	{
		return noReasons;
	}

	public void setNoReasons(String noReasons)
	{
		this.noReasons = noReasons;
	}

	public String getConfidence()
	{
		return confidence;
	}

	public void setConfidence(String confidence)
	{
		this.confidence = confidence;
	}

	public String getConditions()
	{
		return conditions;
	}

	public void setConditions(String conditions)
	{
		this.conditions = conditions;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
