package org.felix.db;

public class Product
{

	protected String replace(String old)
	{
		if (old == null) return null;
		// SQL String
		return old.replace("\'", "''");
	}

}
