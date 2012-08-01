package org.felix.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.felix.db.Environment;
import org.felix.db.PilotStudy;
import org.felix.db.Tee;
import org.felix.db.User;
import org.felix.db.VirtualRating;
import org.felix.db.dao.Tee80sDao;
import org.felix.math.RandomUtils;

public class TShirtServlet extends HttpServlet
{
	private final static Tee80sDao	dao					= new Tee80sDao();
	// all tees
	private final static List<Tee>	tees				= dao.queryTees();

	private static final long		serialVersionUID	= 1L;

	private final static int		maxProgress			= 2;
	private final static int		pageSize			= 10;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		String teeId = req.getParameter("teeId");
		Integer userId = (Integer) req.getSession().getAttribute("userId");
		String environment = (String) req.getSession().getAttribute("environment");

		if (action == null)
		{
			int id = dao.getUserId() + 1;
			req.setAttribute("userId", id);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else if ("admin".equals(action))
		{
			req.getRequestDispatcher("manage.jsp").forward(req, resp);
		} else if ("clearDB".equals(action))
		{
			try
			{
				dao.clearDB();
				String msg = "Users\' data is cleared. ";
				finishStudy(resp, msg);
			} catch (Exception e)
			{
				e.printStackTrace();
				String msg = "Users\' data is not cleared as errors \'" + e.getMessage() + "\' occurs";
				finishStudy(resp, msg);
			}
		} else if ("clearPilots".equals(action))
		{
			try
			{
				dao.clearPilots();
				String msg = "All pilot studies are cleared. ";
				finishStudy(resp, msg);
			} catch (Exception e)
			{
				e.printStackTrace();
				String msg = "All pilot studies are not cleared as errors \'" + e.getMessage() + "\' occurs";
				finishStudy(resp, msg);
			}
		} else if ("clearRatings".equals(action))
		{
			try
			{
				dao.clearRatings();
				String msg = "All virtual ratings are cleared. ";
				finishStudy(resp, msg);
			} catch (Exception e)
			{
				e.printStackTrace();
				String msg = "All virtual ratings are not cleared as errors \'" + e.getMessage() + "\' occurs";
				finishStudy(resp, msg);
			}
		} else if ("pilot".equals(action))
		{
			req.getRequestDispatcher("pilot.jsp").forward(req, resp);
		} else if ("pilotProgress".equals(action))
		{
			List<PilotStudy> ps = dao.queryPilots();
			req.setAttribute("records", ps);
			req.getRequestDispatcher("pilotProgress.jsp").forward(req, resp);
		} else if ("pilot_sub".equals(action))
		{
			PilotStudy p = new PilotStudy();
			p.setAppearance(Integer.parseInt(req.getParameter("appearance")));
			p.setMaterial(Integer.parseInt(req.getParameter("material")));
			p.setFit(Integer.parseInt(req.getParameter("fit")));
			p.setSituation(Integer.parseInt(req.getParameter("situation")));
			p.setCustomization(Integer.parseInt(req.getParameter("customization")));
			p.setRating(Integer.parseInt(req.getParameter("rating")));
			p.setBrand(Integer.parseInt(req.getParameter("brand")));
			p.setStore(Integer.parseInt(req.getParameter("store")));
			p.setRecommendation(Integer.parseInt(req.getParameter("recommendation")));
			p.setCategory(Integer.parseInt(req.getParameter("category")));
			p.setWarranty(Integer.parseInt(req.getParameter("warranty")));
			p.setPrice(Integer.parseInt(req.getParameter("price")));
			p.setPromotion(Integer.parseInt(req.getParameter("promotion")));
			p.setShipping(Integer.parseInt(req.getParameter("shipping")));

			String otherFeature = req.getParameter("otherFeature").trim();
			if (!otherFeature.isEmpty())
			{
				p.setOtherFeature(otherFeature);
				p.setOthers(Integer.parseInt(req.getParameter("others")));
			}

			p.setComments(req.getParameter("comments"));
			p.setcDate(new Date(System.currentTimeMillis()));

			// auto insert, no consideration of frauds
			dao.insert(p);
			req.setAttribute("thanks", "Your ratings are saved. Thanks for your greate support.");
			req.getRequestDispatcher("./userStudy?action=pilot").forward(req, resp);

		} else if ("env".equals(action))
		{
			if ((Integer) req.getSession().getAttribute("step") == 1) req.getSession().setAttribute("step", 2);
			req.setAttribute("env", dao.queryEnvironment(userId, environment));
			req.getRequestDispatcher("env.jsp").forward(req, resp);
		} else if ("env_sub".equals(action))
		{
			int confidence = Integer.parseInt(req.getParameter("confidence"));
			int presence = Integer.parseInt(req.getParameter("presence"));
			int comfort = Integer.parseInt(req.getParameter("comfort"));
			String reasons = req.getParameter("reasons");

			Environment env = new Environment();
			env.setUserId(userId);
			env.setEnvironment(environment);
			env.setConfidence(confidence);
			env.setPresence(presence);
			env.setComfort(comfort);
			env.setReasons(reasons);
			env.setcDate(new Date(System.currentTimeMillis()));

			// insert into database
			if (dao.queryEnvironment(userId, environment) == null) dao.insert(env);
			else dao.update(env);

			req.getSession().setAttribute("step", 3);
			req.getRequestDispatcher("ack.jsp").forward(req, resp);

		} else if ("info".equals(action))
		{
			/*
			 * when teeId == null, start to rate new t-shirt, otherwise continue current t-shirt
			 */
			if (teeId == null)
			{
				/* key: progress; value: teeId */
				Map<Integer, String> visitedTees = (Map<Integer, String>) req.getSession().getAttribute("vTees");
				if (visitedTees == null) visitedTees = new HashMap<>();

				/* how many t-shirts are rated */
				int progress = visitedTees.size();
				if (progress > maxProgress)
				{
					String msg = "Thanks for your cooperation. This user study is finished.";
					finishStudy(resp, msg);

					return;
				}

				String next = req.getParameter("survey");
				if ((progress == 0) || (next != null && next.equals("next")))
				{
					/* prepare next survey t-shirt */
					List<VirtualRating> rs = dao.queryVirtualRatings(userId);

					boolean found = false;
					while (true)
					{
						found = false;
						int index = RandomUtils.randInt(tees.size());
						Tee t = tees.get(index);
						for (VirtualRating r : rs)
						{
							if (r.getTeeId().equals(t.getId()))
							{
								found = true;
								break;
							}
						}
						if (!found)
						{
							teeId = t.getId();
							break;
						}
					}

					progress++;
					visitedTees.put(progress, teeId);

					req.getSession().setAttribute("progress", progress);
					req.getSession().setAttribute("vTees", visitedTees); // visited tees
					req.getSession().setAttribute("maxProgress", maxProgress);

				} else
				{
					teeId = visitedTees.get(progress);
				}
			}

			if (req.getSession().getAttribute("vrProgress") == null) req.getSession().setAttribute("vrProgress", 1);
			if (req.getSession().getAttribute("step") == null) req.getSession().setAttribute("step", 1);

			String page = req.getParameter("page");
			int p = (page == null ? 1 : Integer.parseInt(page));
			req.setAttribute("page", p);

			req.setAttribute("tee", dao.queryTee(teeId));
			req.setAttribute("rating", dao.queryVirtualRating(userId, teeId));
			req.setAttribute("reviews", dao.queryReviews(teeId, p, pageSize));

			req.getRequestDispatcher("t-shirt.jsp").forward(req, resp);

		} else if ("rating".equals(action))
		{
			String comments = req.getParameter("comments");

			int overall = Integer.parseInt(req.getParameter("overall"));
			int appearance = Integer.parseInt(req.getParameter("appearance"));
			int material = Integer.parseInt(req.getParameter("material"));
			int fit = Integer.parseInt(req.getParameter("fit"));
			int category = Integer.parseInt(req.getParameter("category"));
			int price = Integer.parseInt(req.getParameter("price"));
			int brand = Integer.parseInt(req.getParameter("brand"));
			int store = Integer.parseInt(req.getParameter("store"));
			int shipping = Integer.parseInt(req.getParameter("shipping"));
			int quality = Integer.parseInt(req.getParameter("quality"));
			int cost = Integer.parseInt(req.getParameter("cost"));
			int value = Integer.parseInt(req.getParameter("value"));

			VirtualRating r = new VirtualRating();
			r.setUserId(userId);
			r.setTeeId(teeId);
			r.setComments(comments);
			r.setEnvironment(environment);
			r.setcDate(new Date(System.currentTimeMillis()));

			r.setOverall(overall);
			r.setAppearance(appearance);
			r.setMaterial(material);
			r.setFit(fit);
			r.setCategory(category);
			r.setPrice(price);
			r.setBrand(brand);
			r.setStore(store);
			r.setShipping(shipping);
			r.setQuality(quality);
			r.setCost(cost);
			r.setValue(value);

			OutputStream os = resp.getOutputStream();
			if (dao.queryVirtualRating(r.getUserId(), r.getTeeId()) != null)
			{
				dao.update(r);
				os.write(("Thanks. Your ratings and comments are updated.").getBytes());
			} else
			{
				dao.insert(r);

				if (req.getSession().getAttribute("environment").equals("virtual reality"))
				{
					int vrProgress = (Integer) req.getSession().getAttribute("vrProgress") + 1;
					req.getSession().setAttribute("vrProgress", vrProgress);
				}

				os.write(("Thanks. Your ratings and comments are saved.").getBytes());
			}
			os.close();
		} else if ("user".equals(action))
		{
			environment = req.getParameter("environment");

			User u = new User();
			u.setGender(req.getParameter("gender"));
			String job = req.getParameter("job");
			if (job != null && job.equals("student")) job += "::" + req.getParameter("job1");
			else if (job != null && job.equals("staff")) job += "::" + req.getParameter("job2");
			u.setJob(job);
			u.setAge(req.getParameter("age"));
			u.setEducation(req.getParameter("education"));
			u.setShoppingExperience(req.getParameter("shoppingExperience"));
			u.setVrExperience(req.getParameter("vrExperience"));
			u.setcDate(new Date(System.currentTimeMillis()));

			dao.insert(u);
			userId = dao.getUserId();
			u.setUserId(userId);
			resetSession(req, userId, environment);

			if ("virtual reality".equals(environment))
			{
				String msg = "* New UserID '" + u.getUserId() + "' is created successfully. "
						+ "Please use 'second life viewer (RLV)' to continue this user study.";
				req.setAttribute("error", msg);
				req.setAttribute("user", u);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else
			{
				req.getRequestDispatcher("./userStudy?action=info").forward(req, resp);
			}
		} else if ("user_in".equals(action))
		{
			userId = Integer.parseInt(req.getParameter("userId"));
			environment = req.getParameter("environment");

			if (dao.queryUser(userId) == null)
			{
				req.setAttribute("error_in", "* UserID '" + userId + "' does not exist.");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else
			{
				resetSession(req, userId, environment);

				if ("virtual reality".equals(environment))
				{
					req.setAttribute("error_in", "* Please use 'second life viewer (RLV)' to continue this user study.");
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				} else
				{
					req.getRequestDispatcher("./userStudy?action=info").forward(req, resp);
				}
			}
		}
	}

	private void resetSession(HttpServletRequest req, int userId, String environment)
	{
		req.getSession().setAttribute("userId", userId);
		req.getSession().setAttribute("environment", environment);

		req.getSession().setAttribute("progress", 1);
		req.getSession().setAttribute("vrProgress", 1);
		req.getSession().setAttribute("maxProgress", maxProgress);

		req.getSession().setAttribute("step", 1);
		req.getSession().removeAttribute("vTees");
	}

	private void finishStudy(HttpServletResponse resp, String msg) throws IOException
	{
		String out = "<script>alert('" + msg + "');</script>";
		resp.setHeader("Refresh", "5; URL=./userStudy");
		resp.getOutputStream().write(out.getBytes());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

}
