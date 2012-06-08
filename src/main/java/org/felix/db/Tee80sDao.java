package org.felix.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.felix.util.io.FileUtils;
import org.felix.web.client.Tee80sShirtClient;

public class Tee80sDao extends Dao
{
	static
	{
		database = "Tee80sDB";
	}

	void update(Tee80s t) throws Exception
	{
		String sql = "UPDATE tee80s SET name='" + sqlString(t.getName()) + "', sizes='" + sqlString(t.getSizes())
				+ "', price = '" + sqlString(t.getPrice()) + "', features = '" + sqlString(t.getFeatures())
				+ "', gender = '" + sqlString(t.getGender()) + "', type ='" + sqlString(t.getType()) + "', url='"
				+ sqlString(t.getUrl()) + "', description ='" + sqlString(t.getDescription()) + "', image='"
				+ sqlString(t.getImage()) + "', admins='" + sqlString(t.getAdmins()) + "', locale='"
				+ sqlString(t.getLocale()) + "', avgRating = " + t.getAvgRating() + ", numRating = " + t.getNumRating()
				+ " WHERE id='" + t.getId() + "'";

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
		tableElements = "id, name, sizes, price, features, gender, type, url, description, image, admins, locale, avgRating, numRating";
		String sql = "INSERT INTO tee80s (" + tableElements + ") VALUES ('" + t.getId() + "', '"
				+ sqlString(t.getName()) + "', '" + sqlString(t.getSizes()) + "', '" + sqlString(t.getPrice()) + "', '"
				+ sqlString(t.getFeatures()) + "', '" + sqlString(t.getGender()) + "', '" + sqlString(t.getType())
				+ "', '" + sqlString(t.getUrl()) + "', '" + sqlString(t.getDescription()) + "', '"
				+ sqlString(t.getImage()) + "', '" + sqlString(t.getAdmins()) + "', '" + sqlString(t.getLocale())
				+ "', " + t.getAvgRating() + ", " + t.getNumRating() + ")";

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
			t.setType(rs.getString("type"));
			t.setGender(rs.getString("gender"));
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
	protected boolean createTables() throws Exception
	{

		String sql = "CREATE TABLE tee80s (id VARCHAR(20) PRIMARY KEY, name VARCHAR(50), sizes VARCHAR(50), price VARCHAR(50),"
				+ "features VARCHAR(200), gender VARCHAR(10) DEFAULT 'mens', type VARCHAR(50), url VARCHAR(500), "
				+ "description VARCHAR(2000), image VARCHAR(500), admins VARCHAR(50), locale VARCHAR(50), avgRating FLOAT, numRating INTEGER )";

		logger.info("Create table tee80s: {}", sql);
		stmt.execute(sql);
		
		sql = "CREATE TABLE reviews (id GENERATED ALWAYS AS IDENTITY, productId VARCHAR(20) NOT NULL, rating FLOAT, userName VARCHAR(50), userLocation VARCHAR(100), tags VARCHAR(100),"
				+ "title VARCHAR(100), details VARCHAR(2000), pros VARCHAR(200), cons VARCHAR(200), "
				+ "bestUses VARCHAR(200), fit VARCHAR(100), length VARCHAR(100), gift VARCHAR(100), recommendation VARCHAR(200) )";
		
		logger.info("Create table reviews: {}", sql);

		return stmt.execute(sql);

	};

	@Override
	protected boolean dropTables() throws Exception
	{
		String sql = "DROP TABLE tee80s";
		stmt.execute(sql);
		
		sql = "DROP TABLE reviews";
		return stmt.execute(sql);
	}

	public static void main(String[] args) throws Exception
	{
		Tee80sDao dao = new Tee80sDao();
		// dao.dropTable();
		dao.createTables();

		Tee80sShirtClient client = new Tee80sShirtClient();
		List<String> links = FileUtils.readAsList("links.txt");
		for (String link : links)
		{
			Tee80s t = new Tee80s();
			t.setName(link.split("::")[2]);

			client.searchTee80s(t);
			dao.insert(t);
		}
	}

}
