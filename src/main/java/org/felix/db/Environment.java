package org.felix.db;
 
import java.sql.Date;

public class Environment extends DBObject
{
	private int		userId;
	private int		confidence;
	private int		presence;
	private int		comfort;
	private String	reasons;
	private String	environment;
	private Date	cDate;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getConfidence()
	{
		return confidence;
	}

	public void setConfidence(int confidence)
	{
		this.confidence = confidence;
	}

	public int getPresence()
	{
		return presence;
	}

	public void setPresence(int presence)
	{
		this.presence = presence;
	}

	public int getComfort()
	{
		return comfort;
	}

	public void setComfort(int comfort)
	{
		this.comfort = comfort;
	}

	public String getReasons()
	{
		return reasons;
	}

	public void setReasons(String reasons)
	{
		this.reasons = replace(reasons);
	}

	public String getEnvironment()
	{
		return environment;
	}

	public void setEnvironment(String environment)
	{
		this.environment = replace(environment);
	}

	public Date getcDate()
	{
		return cDate;
	}

	public void setcDate(Date cDate)
	{
		this.cDate = cDate;
	}
}
