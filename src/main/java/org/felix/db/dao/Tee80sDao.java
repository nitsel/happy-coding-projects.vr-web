package org.felix.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.felix.db.Environment;
import org.felix.db.PilotStudy;
import org.felix.db.Review;
import org.felix.db.Tee;
import org.felix.db.User;
import org.felix.db.VirtualRating;
import org.felix.io.FileUtils;
import org.felix.io.URLReader;
import org.felix.system.DateUtils;
import org.felix.system.Timer;
import org.felix.web.client.Tee80sShirtClient;
 
public class Tee80sDao extends DerbyDao
{
	static
	{
		database = "Tee80sDB";
	}

	public int getUserId()
	{
		String sql = "SELECT COUNT(*) AS numUsers FROM users";
		ResultSet rs = null;
		int userId = 0;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) userId = Integer.parseInt(rs.getString("numUsers"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return userId;
	}

	public int getUserId(User u)
	{
		String sql = "SELECT * FROM users WHERE gender='" + u.getGender() + "' AND age='" + u.getAge()
				+ "' AND education='" + u.getEducation() + "' AND job='" + u.getJob() + "' AND shoppingExperience='"
				+ u.getShoppingExperience() + "' AND vrExperience='" + u.getVrExperience() + "' AND cDate='"
				+ u.getcDate() + "'";
		ResultSet rs = null;
		int userId = 0;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) userId = Integer.parseInt(rs.getString("userId"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return userId;
	}

	public void update(User u)
	{
		String sql = "UPDATE users SET age='" + u.getAge() + "', gender='" + u.getGender() + "', education='"
				+ u.getEducation() + "', job='" + u.getJob() + "', shoppingExperience='" + u.getShoppingExperience()
				+ "', vrExperience='" + u.getVrExperience() + "' WHERE userId=" + u.getUserId();
		logger.debug("Update users: {}", sql);

		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Environment env)
	{
		String sql = "UPDATE envs SET confidence=" + env.getConfidence() + ", presence=" + env.getPresence()
				+ ", comfort=" + env.getComfort() + ", reasons='" + env.getReasons() + "', cDate='" + env.getcDate()
				+ "' WHERE userId=" + env.getUserId() + " AND environment='" + env.getEnvironment() + "'";
		logger.debug("Update envs: {}", sql);

		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Tee t) throws Exception
	{
		String sql = "UPDATE tee80s SET name='" + t.getName() + "', category = '" + t.getCategory() + "', sizes='"
				+ t.getSizes() + "', price = '" + t.getPrice() + "', features = '" + t.getFeatures() + "', gender = '"
				+ t.getGender() + "', type ='" + t.getType() + "', url='" + t.getUrl() + "', description ='"
				+ t.getDescription() + "', image='" + t.getImage() + "', admins='" + t.getAdmins() + "', locale='"
				+ t.getLocale() + "', avgRating = " + t.getAvgRating() + ", numRating = " + t.getNumRating()
				+ ", pros='" + t.getPros() + "', cons='" + t.getCons() + "', bestUses='" + t.getBestUses()
				+ "', reviewerProfile='" + t.getReviewerProfile() + "', gift='" + t.getGift() + "', recommendation='"
				+ t.getRecommendation() + "' WHERE id='" + t.getId() + "'";

		logger.debug("Update tee80s: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void update(VirtualRating r)
	{
		String sql = "UPDATE ratings SET environment = '" + r.getEnvironment() + "', comments = '" + r.getComments()
				+ "', cDate = '" + r.getcDate() + "', overall=" + r.getOverall() + ", appearance=" + r.getAppearance()
				+ ", material=" + r.getMaterial() + ", fit=" + r.getFit() + ", category=" + r.getCategory()
				+ ", price=" + r.getPrice() + ", brand=" + r.getBrand() + ", store=" + r.getStore() + ", shipping="
				+ r.getShipping() + ", quality=" + r.getQuality() + ", cost=" + r.getCost() + ", value=" + r.getValue()
				+ " WHERE userId = " + r.getUserId() + " AND teeId = '" + r.getTeeId() + "'";

		logger.debug("Update ratings: {}", sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(PilotStudy p)
	{
		String sql = "UPDATE pilots SET appearance = " + p.getAppearance() + ", material = " + p.getMaterial()
				+ ", fit = " + p.getFit() + ", situation = " + p.getSituation() + ", customization ="
				+ p.getCustomization() + ", rating = " + p.getRating() + ", brand = " + p.getBrand() + ", store = "
				+ p.getStore() + ", recommendation = " + p.getRecommendation() + ", category = " + p.getCategory()
				+ ", warranty = " + p.getWarranty() + ", price = " + p.getPrice() + ", promotion = " + p.getPromotion()
				+ ", shipping = " + p.getShipping() + ", otherFaeture = '" + p.getOtherFeature() + "', others = "
				+ p.getOthers() + ", comments = '" + p.getComments() + "', cDate = '" + p.getcDate()
				+ "' WHERE userId = " + p.getUserId();

		logger.debug("Update pilots: {}", sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(Review r) throws Exception
	{
		String sql = "UPDATE tee80s SET productId='" + r.getProductId() + "', rating=" + r.getRating() + ", userName='"
				+ r.getUserName() + "', userLocation='" + r.getUserLocation() + "', tags = '" + r.getTags()
				+ "', title = '" + r.getTitle() + "', comments = '" + r.getComments() + ", services = '"
				+ r.getServices() + "', pros ='" + r.getPros() + "', cons='" + r.getCons() + "', bestUses ='"
				+ r.getBestUses() + "', fit='" + r.getFit() + "', length='" + r.getLength() + "', gift='" + r.getGift()
				+ "', recommendation = '" + r.getRecommendation() + "', vDate = '" + r.getvDate() + "' WHERE id='"
				+ r.getId() + "'";

		logger.debug("Update reviews: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void insert(User u)
	{
		String meta = "gender, age, education, job, shoppingExperience, vrExperience, cDate";
		String sql = "INSERT INTO users (" + meta + ") VALUES ('" + u.getGender() + "', '" + u.getAge() + "', '"
				+ u.getEducation() + "', '" + u.getJob() + "', '" + u.getShoppingExperience() + "', '"
				+ u.getVrExperience() + "', '" + u.getcDate() + "')";
		logger.debug("Insert user: {}", sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insert(Environment env)
	{
		String meta = "userId, confidence, presence, comfort, reasons, environment, cDate";
		String sql = "INSERT INTO envs (" + meta + ") VALUES (" + env.getUserId() + ", " + env.getConfidence() + ", "
				+ env.getPresence() + ", " + env.getComfort() + ", '" + env.getReasons() + "', '"
				+ env.getEnvironment() + "', '" + env.getcDate() + "')";
		logger.debug("Insert env: {}", sql);
		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insert(Tee t) throws Exception
	{
		// check if it is already exist
		Tee tt = queryTee(t.getId());
		if (tt != null)
		{
			logger.debug("Tee80s (id = {}) has already been existing in the database. ", t.getId());
			return;
		}

		// insert into database
		String tableElements = "id, name, category, sizes, price, features, gender, type, url, description, image, admins, "
				+ "locale, avgRating, numRating, pros, cons, bestUses, reviewerProfile, gift, recommendation";
		String sql = "INSERT INTO tee80s (" + tableElements + ") VALUES ('" + t.getId() + "', '" + t.getName() + "', '"
				+ t.getCategory() + "', '" + t.getSizes() + "', '" + t.getPrice() + "', '" + t.getFeatures() + "', '"
				+ t.getGender() + "', '" + t.getType() + "', '" + t.getUrl() + "', '" + t.getDescription() + "', '"
				+ t.getImage() + "', '" + t.getAdmins() + "', '" + t.getLocale() + "', " + t.getAvgRating() + ", "
				+ t.getNumRating() + ", '" + t.getPros() + "', '" + t.getCons() + "', '" + t.getBestUses() + "', '"
				+ t.getReviewerProfile() + "', '" + t.getGift() + "', '" + t.getRecommendation() + "')";

		logger.debug("Insert tee: {}", sql);

		stmt.executeUpdate(sql);
	}

	public void insert(VirtualRating r)
	{
		String meta = "userId, teeId, comments, cDate, environment, overall, appearance, material, fit, category, price, brand, store, shipping, quality, cost, value";
		String sql = "INSERT INTO ratings (" + meta + ") VALUES (" + r.getUserId() + ", '" + r.getTeeId() + "', '"
				+ r.getComments() + "', '" + r.getcDate() + "', '" + r.getEnvironment() + "', " + r.getOverall() + ", "
				+ r.getAppearance() + ", " + r.getMaterial() + ", " + r.getFit() + ", " + r.getCategory() + ", "
				+ r.getPrice() + ", " + r.getBrand() + ", " + r.getStore() + ", " + r.getShipping() + ", "
				+ r.getQuality() + ", " + r.getCost() + ", " + r.getValue() + ")";

		logger.debug("Insert rating: {}", sql);

		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void insert(PilotStudy p)
	{
		String meta = "appearance, material, fit, situation, customization, rating, brand, store, recommendation, category, warranty, price, promotion, shipping, otherFeature, others, comments, cDate";
		String sql = "INSERT INTO pilots (" + meta + ") VALUES (" + p.getAppearance() + ", " + p.getMaterial() + ", "
				+ p.getFit() + ", " + p.getSituation() + ", " + p.getCustomization() + ", " + p.getRating() + ", "
				+ p.getBrand() + ", " + p.getStore() + ", " + p.getRecommendation() + ", " + p.getCategory() + ", "
				+ p.getWarranty() + ", " + p.getPrice() + ", " + p.getPromotion() + ", " + p.getShipping() + ", '"
				+ p.getOtherFeature() + "', " + p.getOthers() + ", '" + p.getComments() + "', '" + p.getcDate() + "')";

		logger.debug("Insert pilot: {}", sql);

		try
		{
			stmt.executeUpdate(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public VirtualRating queryVirtualRating(int userId, String teeId)
	{
		String sql = "SELECT * FROM ratings WHERE userId = " + userId + " AND teeId ='" + teeId + "'";
		logger.debug("Query ratings: {}", sql);

		ResultSet rs = null;
		VirtualRating r = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				r = new VirtualRating();
				r.setUserId(Integer.parseInt(rs.getString("userId")));
				r.setTeeId(rs.getString("teeId"));
				r.setComments(rs.getString("comments"));
				r.setcDate(DateUtils.parseString(rs.getString("cDate")));
				r.setEnvironment(rs.getString("environment"));

				r.setOverall(Integer.parseInt(rs.getString("overall")));
				r.setAppearance(Integer.parseInt(rs.getString("appearance")));
				r.setMaterial(Integer.parseInt(rs.getString("material")));
				r.setFit(Integer.parseInt(rs.getString("fit")));
				r.setCategory(Integer.parseInt(rs.getString("category")));
				r.setPrice(Integer.parseInt(rs.getString("price")));
				r.setBrand(Integer.parseInt(rs.getString("brand")));
				r.setStore(Integer.parseInt(rs.getString("store")));
				r.setShipping(Integer.parseInt(rs.getString("shipping")));
				r.setQuality(Integer.parseInt(rs.getString("quality")));
				r.setCost(Integer.parseInt(rs.getString("cost")));
				r.setValue(Integer.parseInt(rs.getString("value")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return r;
	}

	// no repeat tees in different envs to remove memory effects. 
	public List<VirtualRating> queryVirtualRatings(int userId)
	{
		String sql = "SELECT * FROM ratings WHERE userId = " + userId;
		logger.debug("Query ratings: {}", sql);

		List<VirtualRating> trs = new ArrayList<>();
		ResultSet rs = null;
		VirtualRating r = null;
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				r = new VirtualRating();
				r.setUserId(Integer.parseInt(rs.getString("userId")));
				r.setTeeId(rs.getString("teeId"));
				r.setComments(rs.getString("comments"));
				r.setcDate(DateUtils.parseString(rs.getString("cDate")));
				r.setEnvironment(rs.getString("environment"));

				r.setOverall(Integer.parseInt(rs.getString("overall")));
				r.setAppearance(Integer.parseInt(rs.getString("appearance")));
				r.setMaterial(Integer.parseInt(rs.getString("material")));
				r.setFit(Integer.parseInt(rs.getString("fit")));
				r.setCategory(Integer.parseInt(rs.getString("category")));
				r.setPrice(Integer.parseInt(rs.getString("price")));
				r.setBrand(Integer.parseInt(rs.getString("brand")));
				r.setStore(Integer.parseInt(rs.getString("store")));
				r.setShipping(Integer.parseInt(rs.getString("shipping")));
				r.setQuality(Integer.parseInt(rs.getString("quality")));
				r.setCost(Integer.parseInt(rs.getString("cost")));
				r.setValue(Integer.parseInt(rs.getString("value")));
				trs.add(r);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return trs;
	}

	public List<VirtualRating> queryVirtualRatings()
	{
		String sql = "SELECT * FROM ratings";
		logger.debug("Query ratings: {}", sql);

		List<VirtualRating> trs = new ArrayList<>();
		ResultSet rs = null;
		VirtualRating r = null;
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				r = new VirtualRating();
				r.setUserId(Integer.parseInt(rs.getString("userId")));
				r.setTeeId(rs.getString("teeId"));
				r.setComments(rs.getString("comments"));
				r.setcDate(DateUtils.parseString(rs.getString("cDate")));
				r.setEnvironment(rs.getString("environment"));

				r.setOverall(Integer.parseInt(rs.getString("overall")));
				r.setAppearance(Integer.parseInt(rs.getString("appearance")));
				r.setMaterial(Integer.parseInt(rs.getString("material")));
				r.setFit(Integer.parseInt(rs.getString("fit")));
				r.setCategory(Integer.parseInt(rs.getString("category")));
				r.setPrice(Integer.parseInt(rs.getString("price")));
				r.setBrand(Integer.parseInt(rs.getString("brand")));
				r.setStore(Integer.parseInt(rs.getString("store")));
				r.setShipping(Integer.parseInt(rs.getString("shipping")));
				r.setQuality(Integer.parseInt(rs.getString("quality")));
				r.setCost(Integer.parseInt(rs.getString("cost")));
				r.setValue(Integer.parseInt(rs.getString("value")));
				trs.add(r);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return trs;
	}

	public void insert(Review r) throws Exception
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

		logger.debug("Insert review: {}", sql);

		stmt.executeUpdate(sql);
	}

	public void delete(Tee t) throws Exception
	{
		String sql = "DELETE FROM tee80s WHERE id = '" + t.getId() + "'";

		logger.debug("Delete tee80s: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void delete(User u) throws Exception
	{
		String sql = "DELETE FROM users WHERE userId = " + u.getUserId();

		logger.debug("Delete users: {}", sql);
		stmt.executeUpdate(sql);
	}

	public void delete(Review r) throws Exception
	{
		String sql = "DELETE FROM reviews WHERE id = '" + r.getId() + "'";

		logger.debug("Delete reviews: {}", sql);
		stmt.executeUpdate(sql);
	}

	public List<Tee> queryTees()
	{
		String sql = "SELECT * FROM tee80s";
		List<Tee> ts = new ArrayList<>();

		ResultSet rs = null;
		Tee t = null;
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				t = new Tee();
				t.setId(rs.getString("id"));
				t.setName(rs.getString("name"));
				t.setCategory(rs.getString("category"));
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

				ts.add(t);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return ts;
	}

	public Tee queryTee(String id)
	{
		String sql = "SELECT * FROM tee80s WHERE id = '" + id + "'";
		logger.debug("Query tee80s: {}", sql);

		ResultSet rs = null;
		Tee t = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				t = new Tee();
				t.setId(id);
				t.setName(rs.getString("name"));
				t.setCategory(rs.getString("category"));
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

	public ResultSet queryReview(Review r) throws Exception
	{
		String sql = "SELECT * FROM reviews WHERE productId = '" + r.getProductId() + "' AND userName = '"
				+ r.getUserName() + "'";

		logger.debug("Query reviews: {}", sql);
		return stmt.executeQuery(sql);
	}

	public User queryUser(int userId)
	{
		String sql = "SELECT * FROM users WHERE userId = " + userId;
		logger.debug("Query users: {}", sql);

		ResultSet rs;
		User u = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				u = new User();
				u.setUserId(Integer.parseInt(rs.getString("userId")));
				u.setAge(rs.getString("age"));
				u.setGender(rs.getString("gender"));
				u.setEducation(rs.getString("education"));
				u.setJob(rs.getString("job"));
				u.setShoppingExperience(rs.getString("shoppingExperience"));
				u.setVrExperience(rs.getString("vrExperience"));
				u.setcDate(DateUtils.parseString(rs.getString("cDate")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return u;
	}

	public List<User> queryUsers()
	{
		String sql = "SELECT * FROM users";
		logger.debug("Query users: {}", sql);

		ResultSet rs;
		List<User> users = new ArrayList<>();
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				User u = new User();
				u.setUserId(Integer.parseInt(rs.getString("userId")));
				u.setAge(rs.getString("age"));
				u.setGender(rs.getString("gender"));
				u.setEducation(rs.getString("education"));
				u.setJob(rs.getString("job"));
				u.setShoppingExperience(rs.getString("shoppingExperience"));
				u.setVrExperience(rs.getString("vrExperience"));
				u.setcDate(DateUtils.parseString(rs.getString("cDate")));

				users.add(u);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return users;
	}

	public List<PilotStudy> queryPilots()
	{
		String sql = "SELECT * FROM pilots";
		logger.debug("Query pilots: {}", sql);

		ResultSet rs;
		List<PilotStudy> ps = new ArrayList<>();

		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				PilotStudy p = new PilotStudy();
				p.setUserId(Integer.parseInt(rs.getString("userId")));
				p.setAppearance(Integer.parseInt(rs.getString("appearance")));
				p.setMaterial(Integer.parseInt(rs.getString("material")));
				p.setFit(Integer.parseInt(rs.getString("fit")));
				p.setSituation(Integer.parseInt(rs.getString("situation")));
				p.setCustomization(Integer.parseInt(rs.getString("customization")));
				p.setRating(Integer.parseInt(rs.getString("rating")));
				p.setBrand(Integer.parseInt(rs.getString("brand")));
				p.setStore(Integer.parseInt(rs.getString("store")));
				p.setRecommendation(Integer.parseInt(rs.getString("recommendation")));
				p.setCategory(Integer.parseInt(rs.getString("category")));
				p.setWarranty(Integer.parseInt(rs.getString("warranty")));
				p.setPrice(Integer.parseInt(rs.getString("price")));
				p.setPromotion(Integer.parseInt(rs.getString("promotion")));
				p.setShipping(Integer.parseInt(rs.getString("shipping")));
				p.setOthers(Integer.parseInt(rs.getString("others")));
				p.setOtherFeature(rs.getString("otherFeature"));
				p.setComments(rs.getString("comments"));
				p.setcDate(DateUtils.parseString(rs.getString("cDate")));

				ps.add(p);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return ps;
	}

	public Environment queryEnvironment(int userId, String environment)
	{
		String sql = "SELECT * FROM envs WHERE userId = " + userId + " AND environment = '" + environment + "'";
		logger.debug("Query envs: {}", sql);

		ResultSet rs;
		Environment env = null;
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				env = new Environment();
				env.setUserId(Integer.parseInt(rs.getString("userId")));
				env.setConfidence(Integer.parseInt(rs.getString("confidence")));
				env.setComfort(Integer.parseInt(rs.getString("comfort")));
				env.setPresence(Integer.parseInt(rs.getString("presence")));
				env.setReasons(rs.getString("reasons"));
				env.setEnvironment(rs.getString("environment"));
				env.setcDate(DateUtils.parseString(rs.getString("cDate")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return env;
	}

	public List<Environment> queryEnvironments()
	{
		String sql = "SELECT * FROM envs";
		logger.debug("Query envs: {}", sql);

		ResultSet rs;
		List<Environment> envs = new ArrayList<>();
		try
		{
			rs = stmt.executeQuery(sql);
			if (rs.next())
			{
				Environment env = new Environment();
				env.setUserId(Integer.parseInt(rs.getString("userId")));
				env.setConfidence(Integer.parseInt(rs.getString("confidence")));
				env.setComfort(Integer.parseInt(rs.getString("comfort")));
				env.setPresence(Integer.parseInt(rs.getString("presence")));
				env.setReasons(rs.getString("reasons"));
				env.setEnvironment(rs.getString("environment"));
				env.setcDate(DateUtils.parseString(rs.getString("cDate")));

				envs.add(env);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return envs;
	}

	public Review queryReview(String reviewId) throws Exception
	{
		String sql = "SELECT * FROM reviews WHERE id = '" + reviewId + "'";
		logger.debug("Query reviews: {}", sql);

		ResultSet rs = stmt.executeQuery(sql);
		Review r = null;
		if (rs.next())
		{
			r = new Review();
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

	public List<Review> queryReviews(String productId)
	{
		return queryReviews(productId, 0, 0);
	}

	public List<Review> queryReviews(String productId, int page, int pageSize)
	{
		String sql = "SELECT * FROM reviews WHERE productId = '" + productId + "' ORDER BY vDate DESC"
				+ (page > 0 ? " OFFSET " + (page - 1) * pageSize + " ROWS" : "")
				+ (pageSize > 0 ? " FETCH NEXT " + pageSize + " ROWS ONLY" : "");
		logger.debug("Query reviews: {}", sql);

		ResultSet rs = null;
		List<Review> ls = new ArrayList<>();
		try
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				Review r = new Review();
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

	public void createUsers() throws Exception
	{
		String sql = "CREATE TABLE users (userId INT GENERATED ALWAYS AS IDENTITY, gender VARCHAR(50) DEFAULT 'Male', age VARCHAR(50), education VARCHAR(100), job VARCHAR(100), shoppingExperience VARCHAR(100), vrExperience VARCHAR(50), cDate DATE)";
		createTable(sql);
	}

	public void createPilots() throws Exception
	{
		String sql = "CREATE TABLE pilots (userId INT GENERATED ALWAYS AS IDENTITY, appearance INT, material INT, fit INT, situation INT, customization INT, rating INT, brand INT, store INT, recommendation INT, category INT, warranty INT, price INT, promotion INT, shipping INT, others INT, otherFeature VARCHAR(50), comments VARCHAR(1000), cDate DATE)";
		createTable(sql);
	}

	public void createEnvs() throws Exception
	{
		String sql = "CREATE TABLE envs (userId INT, confidence INT, presence INT, comfort INT, reasons VARCHAR(2000), environment VARCHAR(50), cDate DATE, PRIMARY KEY (userId, environment))";
		createTable(sql);
	}

	public void createReviews() throws Exception
	{
		String sql = "CREATE TABLE reviews (id INT GENERATED ALWAYS AS IDENTITY, productId VARCHAR(20) NOT NULL, rating FLOAT, userName VARCHAR(50), userLocation VARCHAR(100), tags VARCHAR(100),"
				+ "title VARCHAR(100), comments VARCHAR(2000), services VARCHAR(1000), pros VARCHAR(200), cons VARCHAR(200), bestUses VARCHAR(200), fit VARCHAR(100), length VARCHAR(100), gift VARCHAR(100), recommendation VARCHAR(200), vDate DATE )";

		createTable(sql);
	}

	public void createRatings() throws Exception
	{
		String sql = "CREATE TABLE ratings (userId INT, teeId VARCHAR(50) NOT NULL, comments VARCHAR(2000), cDate DATE, environment VARCHAR(50), overall INT, appearance INT, material INT, fit INT, category INT, price INT, brand INT, store INT, shipping INT, quality INT, cost INT, value INT, PRIMARY KEY (userId, teeId))";
		createTable(sql);
	}

	public void createTees() throws Exception
	{
		String sql = "CREATE TABLE tee80s (id VARCHAR(20) PRIMARY KEY, name VARCHAR(50), category VARCHAR(50), "
				+ "sizes VARCHAR(500), price VARCHAR(50), features VARCHAR(200), gender VARCHAR(10) DEFAULT 'mens', "
				+ "type VARCHAR(50), url VARCHAR(500), description VARCHAR(2000), image VARCHAR(500), "
				+ "admins VARCHAR(50), locale VARCHAR(50), avgRating FLOAT, numRating INTEGER, pros VARCHAR(200), "
				+ "cons VARCHAR(200), bestUses VARCHAR(200), reviewerProfile VARCHAR(200), gift VARCHAR(50), "
				+ "recommendation VARCHAR(100) )";

		createTable(sql);
	}

	@Override
	protected void createTables() throws Exception
	{
		createPilots();
		createTees();
		createUsers();
		createEnvs();
		createRatings();
		createReviews();
	}

	public void dropUsers() throws Exception
	{
		String sql = "DROP TABLE tee80s";
		stmt.execute(sql);
	}

	public void clearDB() throws Exception
	{
		clearRatings();
		clearPilots();
	}

	public void clearRatings() throws Exception
	{
		String[] tables = { "users", "envs", "ratings" };
		clearTables(tables);
	}

	public void clearPilots() throws Exception
	{
		clearTable("pilots");
	}

	public void buildDB() throws Exception
	{
		// FileUtils.deleteDirectory(database);

		createTables();

		Tee80sShirtClient client = new Tee80sShirtClient();
		List<String> links = FileUtils.readAsList("links.txt");
		for (String link : links)
		{
			Tee t = new Tee();
			t.setName(link.split("::")[2]);

			/* Tees */
			String dirPath = "./src/main/webapp/Htmls/";
			String html = FileUtils.readAsString(dirPath + t.getName() + ".htm");
			client.parseTee80s(html, t);
			insert(t);

			/* Reviews */
			boolean reviewFlag = true;
			if (reviewFlag)
			{
				int pages = t.getNumRating() / 10 + (t.getNumRating() % 10 == 0 ? 0 : 1);
				for (int i = 1; i < pages + 1; i++)
				{
					String page = i == 1 ? "" : "-" + i;
					html = FileUtils.readAsString(dirPath + t.getName() + page + ".htm");
					List<Review> rs = client.parseReview(html);
					for (Review r : rs)
					{
						r.setProductId(t.getId());
						insert(r);
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
				FileUtils.writeCollection("images.txt", imageList, true);
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		Timer.start();
		Tee80sDao dao = new Tee80sDao();
		dao.buildDB();

		logger.debug("Consumed {} to be finished.", Timer.end());
	}

}
