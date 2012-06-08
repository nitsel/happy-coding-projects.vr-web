package org.felix.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Tee80sDao extends Dao
{
	static
	{
		database = "Tee80sDB";
		tableElements = "id, name, sizes, price, color, material, features, type, url, description, image, admins, locale, avgRating, numRating";
	}

	void update(Tee80s t) throws Exception
	{
		String sql = "UPDATE tee80s SET name='" + sqlString(t.getName()) + "', sizes='"
				+ sqlString(t.getSizes()) + "', price = '" + sqlString(t.getPrice()) + "', color='" 
				+ sqlString(t.getColor()) + "', material ="	+ sqlString(t.getMaterial()) + ", features = '" 
				+ sqlString(t.getFeatures()) + "', type ='"	+ sqlString(t.getType()) + "', url='" 
				+ sqlString(t.getUrl()) + "', description ='"+ sqlString(t.getDescription()) + "', image=" 
				+ sqlString(t.getImage()) + ", admins='"+ sqlString(t.getAdmins()) + "', locale='" 
				+ sqlString(t.getLocale()) + "', avgRating = "+ t.getAvgRating() + ", numRating = " 
				+ t.getNumRating() + " WHERE id='" + t.getId() + "'";

		logger.info("Update tee80s: {}", sql);
		stmt.executeUpdate(sql);
	}

	void insert(Tee80s t) throws Exception
	{
		// check if it is already exist
		ResultSet rs = query(t);
		if (rs.next())
		{
			logger.debug("Tee80s (id = {}) has already been existing in the database. ", t.getId());
			return;
		}

		// insert into database
		String sql = "INSERT INTO tee80s (" + tableElements + ") VALUES ('" + t.getId() + "', '"
				+ sqlString(t.getName()) + "', '" + sqlString(t.getSizes()) + "', '"
				+ sqlString(t.getPrice()) + "', '" + sqlString(t.getColor()) + "', '"
				+ sqlString(t.getMaterial()) + "', '" + sqlString(t.getFeatures()) + "', '" 
				+ sqlString(t.getType()) + "', '" + sqlString(t.getUrl()) + "', '"
				+ sqlString(t.getDescription()) + "', '" + sqlString(t.getImage()) + "', '" 
				+ sqlString(t.getAdmins()) + "', '" + sqlString(t.getLocale()) + "', "
				+ t.getAvgRating() + ", " + t.getNumRating() + ")";

		logger.info("Insert book: {}", sql);

		stmt.executeUpdate(sql);
	};

	public void delete(Tee80s t) throws Exception
	{
		String sql = "DELETE FROM tee80s WHERE id = '" + t.getId() + "'";

		logger.info("Delete tee80s: {}", sql);
		stmt.executeUpdate(sql);
	};

	public List<String> retrieveAllTee80s() throws Exception
	{
		List<String> results = null;

		String sql = "SELECT * FROM tee80s";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			if (results == null) results = new ArrayList<String>();
			results.add(rs.getString("id"));
		}

		return results;
	}

	public ResultSet query(Tee80s t) throws Exception
	{
		String sql = "SELECT * FROM tee80s WHERE id = '" + t.getId() + "'";

		logger.info("Query tee80s: {}", sql);
		return stmt.executeQuery(sql);
	}

	public Tee80s query(String id) throws Exception
	{
		String sql = "SELECT * FROM tee80s WHERE id = '" + id + "'";
		logger.info("Query tee80s: {}", sql);

		ResultSet rs = stmt.executeQuery(sql);
		Tee80s t = null;
		if (rs.next())
		{
			t = new Tee80s();
			t.setId(id);
			t.setName(rs.getString("name"));
			t.setSizes(rs.getString("sizes"));
			t.setPrice(rs.getString("price"));
			t.setColor(rs.getString("color"));
			t.setMaterial(rs.getString("material"));
			t.setType(rs.getString("type"));
			t.setUrl(rs.getString("url"));
			t.setDescription(rs.getString("description"));
			t.setImage(rs.getString("image"));
			t.setAdmins(rs.getString("admins"));
			t.setLocale(rs.getString("locale"));
			t.setAvgRating(Float.parseFloat(rs.getString("avgRating")));
			t.setNumRating(Integer.parseInt(rs.getString("numRating")));
		}

		return t;
	}

	@Override
	protected boolean createTable() throws Exception
	{

		String sql = "CREATE TABLE tee80s (id VARCHAR(20) PRIMARY KEY, name VARCHAR(50), sizes VARCHAR(50), price VARCHAR(50),"
				+ "color VARCHAR(50), material VARCHAR(50), featires VARCHAR(100), type VARCHAR(50), url VARCHAR(500), "
				+ "description VARCHAR(2000), image VARCHAR(500), admins VARCHAR(50), locale VARCHAR(50), avgRating FLOAT, numRating INTEGER )";

		logger.info("Create table tee80s: {}", sql);
		return stmt.execute(sql);

	};

	@Override
	protected boolean dropTable() throws Exception
	{
		String sql = "DROP TABLE tee80s";
		return stmt.execute(sql);
	}

	public static void main(String[] args) throws Exception
	{
		Tee80sDao dao = new Tee80sDao();
		dao.createTable();
	}

}
