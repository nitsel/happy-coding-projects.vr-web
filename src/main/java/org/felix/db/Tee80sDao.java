package org.felix.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.felix.io.FileUtils;
import org.felix.io.URLReader;
import org.felix.system.DateUtils;
import org.felix.web.client.Tee80sShirtClient;

public class Tee80sDao extends DerbyDao
{
	static
	{
		database = "Tee80sDB";
	}

	public void update(Tee80s t) throws Exception
	{
		String sql = "UPDATE tee80s SET name='" + t.getName() + "', sizes='" + t.getSizes() + "', price = '"
				+ t.getPrice() + "', features = '" + t.getFeatures() + "', gender = '" + t.getGender() + "', type ='"
				+ t.getType() + "', url='" + t.getUrl() + "', description ='" + t.getDescription() + "', image='"
				+ t.getImage() + "', admins='" + t.getAdmins() + "', locale='" + t.getLocale() + "', avgRating = "
				+ t.getAvgRating() + ", numRating = " + t.getNumRating() + ", pros='" + t.getPros() + "', cons='"
				+ t.getCons() + "', bestUses='" + t.getBestUses() + "', reviewerProfile='" + t.getReviewerProfile()
				+ "', gift='" + t.getGift() + "', recommendation='" + t.getRecommendation() + "' WHERE id='"
				+ t.getId() + "'";

		logger.info("Update tee80s: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void update(Tee80sRating r)
	{
		String sql = "UPDATE ratings SET rating = " + r.getRating() + ", comments = '" + r.getComments()
				+ "', rDate = '" + r.getrDate() + "' WHERE userId = '" + r.getUserId() + "' AND teeId = '"
				+ r.getTeeId() + "'";

		logger.info("Update ratings: {}", sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Tee80sReview r) throws Exception
	{
		String sql = "UPDATE tee80s SET productId='" + r.getProductId() + "', rating=" + r.getRating() + ", userName='"
				+ r.getUserName() + "', userLocation='" + r.getUserLocation() + "', tags = '" + r.getTags()
				+ "', title = '" + r.getTitle() + "', comments = '" + r.getComments() + ", services = '"
				+ r.getServices() + "', pros ='" + r.getPros() + "', cons='" + r.getCons() + "', bestUses ='"
				+ r.getBestUses() + "', fit='" + r.getFit() + "', length='" + r.getLength() + "', gift='" + r.getGift()
				+ "', recommendation = '" + r.getRecommendation() + "', vDate = '" + r.getvDate() + "' WHERE id='"
				+ r.getId() + "'";

		logger.info("Update reviews: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void insert(Tee80s t) throws Exception
	{
		// check if it is already exist
		Tee80s tt = queryTee80s(t.getId());
		if (tt != null)
		{
			logger.debug("Tee80s (id = {}) has already been existing in the database. ", t.getId());
			return;
		}

		// insert into database
		String tableElements = "id, name, sizes, price, features, gender, type, url, description, image, admins, "
				+ "locale, avgRating, numRating, pros, cons, bestUses, reviewerProfile, gift, recommendation";
		String sql = "INSERT INTO tee80s (" + tableElements + ") VALUES ('" + t.getId() + "', '" + t.getName() + "', '"
				+ t.getSizes() + "', '" + t.getPrice() + "', '" + t.getFeatures() + "', '" + t.getGender() + "', '"
				+ t.getType() + "', '" + t.getUrl() + "', '" + t.getDescription() + "', '" + t.getImage() + "', '"
				+ t.getAdmins() + "', '" + t.getLocale() + "', " + t.getAvgRating() + ", " + t.getNumRating() + ", '"
				+ t.getPros() + "', '" + t.getCons() + "', '" + t.getBestUses() + "', '" + t.getReviewerProfile()
				+ "', '" + t.getGift() + "', '" + t.getRecommendation() + "')";

		logger.info("Insert tee: {}", sql);

		stmt.executeUpdate(sql);
	}

	public void insert(Tee80sRating r)
	{
		String tableElements = "userId, teeId, rating, comments, rDate";
		String sql = "INSERT INTO ratings (" + tableElements + ") VALUES ('" + r.getUserId() + "', '" + r.getTeeId()
				+ "', " + r.getRating() + ", '" + r.getComments() + "', '" + r.getrDate() + "')";

		logger.info("Insert rating: {}", sql);

		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Tee80sRating queryTee80sRating(String userId, String teeId)
	{
		String sql = "SELECT * FROM ratings WHERE userId = '" + userId + "' AND teeId ='" + teeId + "'";
		logger.info("Query ratings: {}", sql);

		ResultSet rs = null;
		Tee80sRating r = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				r = new Tee80sRating();
				r.setUserId(rs.getString("userId"));
				r.setTeeId(rs.getString("teeId"));
				r.setRating(Float.parseFloat(rs.getString("rating")));
				r.setComments(rs.getString("comments"));
				r.setrDate(DateUtils.parseString(rs.getString("rDate")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return r;
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
		String tableElements = "productId, rating, userName, userLocation, tags, title, comments, services, pros, cons, bestUses, fit, length, gift, recommendation, vDate";
		String sql = "INSERT INTO reviews (" + tableElements + ") VALUES ('" + r.getProductId() + "', " + r.getRating()
				+ ", '" + r.getUserName() + "', '" + r.getUserLocation() + "', '" + r.getTags() + "', '" + r.getTitle()
				+ "', '" + r.getComments() + "', '" + r.getServices() + "', '" + r.getPros() + "', '" + r.getCons()
				+ "', '" + r.getBestUses() + "', '" + r.getFit() + "', '" + r.getLength() + "', '" + r.getGift()
				+ "', '" + r.getRecommendation() + "', '" + r.getvDate() + "')";

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

	public Tee80s queryTee80s(String id)
	{
		String sql = "SELECT * FROM tee80s WHERE id = '" + id + "'";
		logger.info("Query tee80s: {}", sql);

		ResultSet rs = null;
		Tee80s t = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				t = new Tee80s();
				t.setId(id);
				t.setName(rs.getString("name"));
				t.setSizes(rs.getString("sizes"));
				t.setPrice(rs.getString("price"));
				t.setFeatures(rs.getString("features"));
				t.setType(rs.getString("type"));
				t.setGender(rs.getString("gender"));
				t.setUrl(rs.getString("url"));
				t.setDescription(rs.getString("description"));
				t.setImage(rs.getString("image"));
				t.setAdmins(rs.getString("admins"));
				t.setLocale(rs.getString("locale"));
				t.setAvgRating(Float.parseFloat(rs.getString("avgRating")));
				t.setNumRating(Integer.parseInt(rs.getString("numRating")));
				t.setPros(rs.getString("pros"));
				t.setCons(rs.getString("cons"));
				t.setBestUses(rs.getString("bestUses"));
				t.setReviewerProfile(rs.getString("reviewerProfile"));
				t.setGift(rs.getString("gift"));
				t.setRecommendation(rs.getString("recommendation"));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
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
			r.setComments(rs.getString("comments"));
			r.setServices(rs.getString("services"));
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

	public List<Tee80sReview> queryReviews(String productId)
	{
		return queryReviews(productId, 0, 0);
	}
	
	public List<Tee80sReview> queryReviews(String productId, int page, int pageSize)
	{
		String sql = "SELECT * FROM reviews WHERE productId = '" + productId + "' ORDER BY vDate DESC"
				+ (page > 0 ? " OFFSET " + (page - 1) * pageSize + " ROWS" : "")
				+ (pageSize > 0 ? " FETCH NEXT " + pageSize + " ROWS ONLY" : "");
		logger.info("Query reviews: {}", sql);
		
		ResultSet rs = null;
		List<Tee80sReview> ls = new ArrayList<Tee80sReview>();
		try
		{
			rs = stmt.executeQuery(sql);
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
				r.setComments(rs.getString("comments"));
				r.setServices(rs.getString("services"));
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
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ls;
	}

	@Override
	protected boolean createTables() throws Exception
	{
		String sql = "CREATE TABLE tee80s (id VARCHAR(20) PRIMARY KEY, name VARCHAR(50), sizes VARCHAR(50), price VARCHAR(50),"
				+ "features VARCHAR(200), gender VARCHAR(10) DEFAULT 'mens', type VARCHAR(50), url VARCHAR(500), "
				+ "description VARCHAR(2000), image VARCHAR(500), admins VARCHAR(50), locale VARCHAR(50), avgRating FLOAT, numRating INTEGER,"
				+ " pros VARCHAR(200), cons VARCHAR(200), bestUses VARCHAR(200), reviewerProfile VARCHAR(200), gift VARCHAR(50), recommendation VARCHAR(100) )";

		logger.info("Create table tee80s: {}", sql);
		stmt.execute(sql);

		sql = "CREATE TABLE ratings (userId VARCHAR(50) PRIMARY KEY, teeId VARCHAR(50) NOT NULL, rating FLOAT NOT NULL, comments VARCHAR(2000), rDate DATE)";
		logger.info("Create table ratings: {}", sql);
		stmt.execute(sql);

		sql = "CREATE TABLE reviews (id INT GENERATED ALWAYS AS IDENTITY, productId VARCHAR(20) NOT NULL, rating FLOAT, userName VARCHAR(50), userLocation VARCHAR(100), tags VARCHAR(100),"
				+ "title VARCHAR(100), comments VARCHAR(2000), services VARCHAR(1000), pros VARCHAR(200), cons VARCHAR(200), bestUses VARCHAR(200), fit VARCHAR(100), length VARCHAR(100), gift VARCHAR(100), recommendation VARCHAR(200), vDate DATE )";

		logger.info("Create table reviews: {}", sql);

		return stmt.execute(sql);

	};

	@Override
	protected boolean dropTables() throws Exception
	{
		String sql = "DROP TABLE tee80s";
		stmt.execute(sql);

		sql = "DROP TABLE ratings";
		stmt.execute(sql);

		sql = "DROP TABLE reviews";
		return stmt.execute(sql);
	}

	public static void main(String[] args) throws Exception
	{
		boolean meta = true;
		boolean data = true;

		Tee80sDao dao = new Tee80sDao();
		if (meta)
		{
			// dao.clearTables();
			// dao.dropTables();
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

				/* Tees */
				String html = FileUtils.readAsString("./src/main/webapp/Htmls/" + t.getName() + ".htm");
				client.parseTee80s(html, t);
				dao.insert(t);

				/* Reviews */
				boolean reviewFlag = true;
				if (reviewFlag)
				{
					int pages = t.getNumRating() / 10 + (t.getNumRating() % 10 == 0 ? 0 : 1);
					for (int i = 1; i < pages + 1; i++)
					{
						String page = i == 1 ? "" : "-" + i;
						html = FileUtils.readAsString("./src/main/webapp/Htmls/" + t.getName() + page + ".htm");
						List<Tee80sReview> rs = client.parseReview(html);
						for (Tee80sReview r : rs)
						{
							r.setProductId(t.getId());
							dao.insert(r);
						}
					}
				}
			}

			/* Images of Tees */
			boolean images = true;
			if (!images)
			{
				int j = 0;
				for (String link : links)
				{
					j++;
					String[] d = link.split("::");
					String url = d[4];
					String html = URLReader.read(url);

					List<String> imageList = client.parseImages(html);

					FileUtils.writeString("images.txt", j + "\n" + url, true);
					FileUtils.writeList("images.txt", imageList, true);
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

		sql = "DELETE FROM ratings";
		stmt.execute(sql);
		logger.info("Clear data: {}", sql);

		sql = "DELETE FROM tee80s";
		logger.info("Clear data: {}", sql);

		return stmt.execute(sql);
	}

}
