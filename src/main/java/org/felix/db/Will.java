package org.felix.db;

public class Will extends DBObject
{
	private int id;
	private String willingness ;
	private String yesReasons;
	private String noReasons;
	private String confidence;
	private String conditions;
	
	private int		userId;

	public String getWillingness()
	{
		return willingness;
	}

	public void setWillingness(String willingness)
	{
		this.willingness = replace(willingness);
	}

	public String getYesReasons()
	{
		return yesReasons;
	}

	public void setYesReasons(String yesReasons)
	{
		this.yesReasons = replace(yesReasons);
	}

	public String getNoReasons()
	{
		return noReasons;
	}

	public void setNoReasons(String noReasons)
	{
		this.noReasons = replace(noReasons);
	}

	public String getConfidence()
	{
		return confidence;
	}

	public void setConfidence(String confidence)
	{
		this.confidence = replace(confidence);
	}

	public String getConditions()
	{
		return conditions;
	}

	public void setConditions(String conditions)
	{
		this.conditions = replace(conditions);
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
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
