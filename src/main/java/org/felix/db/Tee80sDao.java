package org.felix.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.felix.util.io.FileUtils;
import org.felix.util.system.DateUtils;
import org.felix.web.client.Tee80sShirtClient;

public class Tee80sDao extends Dao
{
	static
	{
		database = "Tee80sDB";
	}

	public void update(Tee80s t) throws Exception
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
	
	public void update(Tee80sReview r) throws Exception
	{
		String sql = "UPDATE tee80s SET productId='" + r.getProductId() + "', rating=" + r.getRating() + ", userName='"
				+ sqlString(r.getUserName()) + "', userLocation='" + sqlString(r.getUserLocation()) + "', tags = '"
				+ sqlString(r.getTags()) + "', title = '" + sqlString(r.getTitle()) + "', details = '"
				+ sqlString(r.getDetails()) + "', pros ='" + sqlString(r.getPros()) + "', cons='"
				+ sqlString(r.getCons()) + "', bestUses ='" + sqlString(r.getBestUses()) + "', fit='"
				+ sqlString(r.getFit()) + "', length='" + sqlString(r.getLength()) + "', gift='"
				+ sqlString(r.getGift()) + "', recommendation = '" + sqlString(r.getRecommendation()) + "', vDate = '"
				+ r.getvDate() + "' WHERE id='" + r.getId() + "'";
		
		logger.info("Update reviews: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void insert(Tee80s t) throws Exception
	{
		// check if it is already exist
		ResultSet rs = queryTee80s(t);
		if (rs.next())
		{
			logger.debug("Tee80s (id = {}) has already been existing in the database. ", t.getId());
			return;
		}

		// insert into database
		String tableElements = "id, name, sizes, price, features, gender, type, url, description, image, admins, locale, avgRating, numRating";
		String sql = "INSERT INTO tee80s (" + tableElements + ") VALUES ('" + t.getId() + "', '"
				+ sqlString(t.getName()) + "', '" + sqlString(t.getSizes()) + "', '" + sqlString(t.getPrice()) + "', '"
				+ sqlString(t.getFeatures()) + "', '" + sqlString(t.getGender()) + "', '" + sqlString(t.getType())
				+ "', '" + sqlString(t.getUrl()) + "', '" + sqlString(t.getDescription()) + "', '"
				+ sqlString(t.getImage()) + "', '" + sqlString(t.getAdmins()) + "', '" + sqlString(t.getLocale())
				+ "', " + t.getAvgRating() + ", " + t.getNumRating() + ")";

		logger.info("Insert book: {}", sql);

		stmt.executeUpdate(sql);
	}
	
	public void insert(Tee80sReview r) throws Exception
	{
		// check if it is already exist
		ResultSet rs = queryReview(r);
		if (rs.next())
		{
			logger.debug("Reviews (productId = {}, userName = {}) has already been existing in the database. ",
					r.getProductId(), r.getUserName());
			return;
		}
		
		// insert into database
		String tableElements = "productId, rating, userName, userLocation, tags, title, details, pros, cons, bestUses, fit, length, gift, recommendation, vDate";
		String sql = "INSERT INTO reviews (" + tableElements + ") VALUES ('" + r.getProductId() + "', " + r.getRating()
				+ ", '" + sqlString(r.getUserName()) + "', '" + sqlString(r.getUserLocation()) + "', '"
				+ sqlString(r.getTags()) + "', '" + sqlString(r.getTitle()) + "', '" + sqlString(r.getDetails())
				+ "', '" + sqlString(r.getPros()) + "', '" + sqlString(r.getCons()) + "', '"
				+ sqlString(r.getBestUses()) + "', '" + sqlString(r.getFit()) + "', '" + sqlString(r.getLength())
				+ "', '" + sqlString(r.getGift()) + "', '" + sqlString(r.getRecommendation()) + "', '" + r.getvDate()
				+ "')";
		
		logger.info("Insert review: {}", sql);
		
		stmt.executeUpdate(sql);
	}

	public void delete(Tee80s t) throws Exception
	{
		String sql = "DELETE FROM tee80s WHERE id = '" + t.getId() + "'";

		logger.info("Delete tee80s: {}", sql);
		stmt.executeUpdate(sql);
	};
	
	public void delete(Tee80sReview r) throws Exception
	{
		String sql = "DELETE FROM reviews WHERE id = '" + r.getId() + "'";
		
		logger.info("Delete reviews: {}", sql);
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

	public ResultSet queryTee80s(Tee80s t) throws Exception
	{
		String sql = "SELECT * FROM tee80s WHERE id = '" + t.getId() + "'";

		logger.info("Query tee80s: {}", sql);
		return stmt.executeQuery(sql);
	}

	public Tee80s queryTee80s(String id) throws Exception
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
	
	public ResultSet queryReview(Tee80sReview r) throws Exception
	{
		String sql = "SELECT * FROM reviews WHERE productId = '" + r.getProductId() + "' AND userName = '"
				+ r.getUserName() + "'";
		
		logger.info("Query reviews: {}", sql);
		return stmt.executeQuery(sql);
	}

	public Tee80sReview queryReview(String reviewId) throws Exception
	{
		String sql = "SELECT * FROM reviews WHERE id = '" + reviewId + "'";
		logger.info("Query reviews: {}", sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		Tee80sReview r = null;
		if (rs.next())
		{
			r = new Tee80sReview();
			r.setId(Integer.parseInt(rs.getString("id")));
			r.setProductId(rs.getString("productId"));
			r.setRating(Float.parseFloat(rs.getString("rating")));
			r.setUserName(rs.getString("userName"));
			r.setUserLocation(rs.getString("userLocation"));
			r.setTags(rs.getString("tags"));
			r.setTitle(rs.getString("title"));
			r.setDetails(rs.getString("details"));
			r.setPros(rs.getString("pros"));
			r.setCons(rs.getString("cons"));
			r.setBestUses(rs.getString("bestUses"));
			r.setFit(rs.getString("fit"));
			r.setLength(rs.getString("length"));
			r.setGift(rs.getString("gift"));
			r.setRecommendation(rs.getString("recommendation"));
			r.setvDate(DateUtils.parseString(rs.getString("vDate")));
		}
		
		return r;
	}

	public List<Tee80sReview> queryReviews(String productId) throws Exception
	{
		String sql = "SELECT * FROM reviews WHERE productId = '" + productId + "'";
		logger.info("Query reviews: {}", sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		List<Tee80sReview> ls = new ArrayList<Tee80sReview>();
		while (rs.next())
		{
			Tee80sReview r = new Tee80sReview();
			r.setId(Integer.parseInt(rs.getString("id")));
			r.setProductId(productId);
			r.setRating(Float.parseFloat(rs.getString("rating")));
			r.setUserName(rs.getString("userName"));
			r.setUserLocation(rs.getString("userLocation"));
			r.setTags(rs.getString("tags"));
			r.setTitle(rs.getString("title"));
			r.setDetails(rs.getString("details"));
			r.setPros(rs.getString("pros"));
			r.setCons(rs.getString("cons"));
			r.setBestUses(rs.getString("bestUses"));
			r.setFit(rs.getString("fit"));
			r.setLength(rs.getString("length"));
			r.setGift(rs.getString("gift"));
			r.setRecommendation(rs.getString("recommendation"));
			r.setvDate(DateUtils.parseString(rs.getString("vDate")));
			
			ls.add(r);
		}
		
		return ls;
	}

	@Override
	protected boolean createTables() throws Exception
	{

		String sql = "CREATE TABLE tee80s (id VARCHAR(20) PRIMARY KEY, name VARCHAR(50), sizes VARCHAR(50), price VARCHAR(50),"
				+ "features VARCHAR(200), gender VARCHAR(10) DEFAULT 'mens', type VARCHAR(50), url VARCHAR(500), "
				+ "description VARCHAR(2000), image VARCHAR(500), admins VARCHAR(50), locale VARCHAR(50), avgRating FLOAT, numRating INTEGER )";

		logger.info("Create table tee80s: {}", sql);
		stmt.execute(sql);
		
		sql = "CREATE TABLE reviews (id INT GENERATED ALWAYS AS IDENTITY, productId VARCHAR(20) NOT NULL, rating FLOAT, userName VARCHAR(50), userLocation VARCHAR(100), tags VARCHAR(100),"
				+ "title VARCHAR(100), details VARCHAR(2000), pros VARCHAR(200), cons VARCHAR(200), bestUses VARCHAR(200), fit VARCHAR(100), length VARCHAR(100), gift VARCHAR(100), recommendation VARCHAR(200), vDate DATE )";
		
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
		boolean meta = true;
		boolean data = true;

		Tee80sDao dao = new Tee80sDao();
		if (!meta)
		{
			dao.clearTables();
			dao.dropTables();
			dao.createTables();
		}
		
		if (data)
		{
			// dao.clearTables();
			Tee80sShirtClient client = new Tee80sShirtClient();
			List<String> links = FileUtils.readAsList("links.txt");
			for (String link : links)
			{
				Tee80s t = new Tee80s();
				t.setName(link.split("::")[2]);
				
				String html = FileUtils.read("./Htmls/" + t.getName() + ".htm");
				client.parseTee80s(html, t);
				dao.insert(t);

				int pages = t.getNumRating() / 10 + (t.getNumRating() % 10 == 0 ? 0 : 1);
				for (int i = 1; i < pages + 1; i++)
				{
					String page = i == 1 ? "" : "-" + i;
					html = FileUtils.read("./Htmls/" + t.getName() + page + ".htm");
					List<Tee80sReview> rs = client.parseReview(html);
					for (Tee80sReview r : rs)
					{
						r.setProductId(t.getId());
						dao.insert(r);
					}
				}
			}
		}
	}

	@Override
	protected boolean clearTables() throws Exception
	{
		String sql = "DELETE FROM reviews";
		stmt.execute(sql);
		logger.info("Clear data: {}", sql);
		
		sql = "DELETE FROM tee80s";
		logger.info("Clear data: {}", sql);

		return stmt.execute(sql);
	}

}
