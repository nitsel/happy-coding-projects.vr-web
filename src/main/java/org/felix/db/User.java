package org.felix.db;

import java.sql.Date;

public class User
{
	private String	userId;
	private String	gender;
	private String	age;				// age region
	private String	education;
	private String	job;
	private String	shoppingExperience;
	private String	vrExperience;
	private Date	cDate;				// create date

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getEducation()
	{
		return education;
	}

	public void setEducation(String education)
	{
		this.education = education;
	}

	public String getJob()
	{
		return job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	public String getShoppingExperience()
	{
		return shoppingExperience;
	}

	public void setShoppingExperience(String shoppingExperience)
	{
		this.shoppingExperience = shoppingExperience;
	}

	public String getVrExperience()
	{
		return vrExperience;
	}

	public void setVrExperience(String vrExperience)
	{
		this.vrExperience = vrExperience;
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
