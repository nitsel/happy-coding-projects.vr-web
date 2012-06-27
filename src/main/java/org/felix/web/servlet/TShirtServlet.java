package org.felix.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.felix.db.Tee80s;
import org.felix.db.Tee80sDao;
import org.felix.db.Tee80sRating;
import org.felix.db.User;
import org.felix.system.RandomUtils;

public class TShirtServlet extends HttpServlet
{
	private final static Tee80sDao dao = new Tee80sDao();
	// all tees
	private final static List<Tee80s> ts = dao.queryAllTee80s();
	
	private static final long serialVersionUID = 1L;

	private final static int maxProgress = 2;
	private final static int pageSize = 10;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");

		String teeId = req.getParameter("teeId");
		String userId = (String) req.getSession().getAttribute("userId");
		if (userId == null)
		{
			userId = "guoguibing";
			req.getSession().setAttribute("userId", userId);
		}

		if (action == null)
		{
			req.getRequestDispatcher("user.jsp").forward(req, resp);
		} else if ("env".equals(action))
		{
			req.getRequestDispatcher("environment.jsp").forward(req, resp);
		} else if ("info".equals(action))
		{
			/*
			 * when teeId == null, start to rate new t-shirt, otherwise continue
			 * current t-shirt
			 */
			if (teeId == null)
			{
				/* key: progress; value: teeId */
				Map<Integer, String> ptMap = (Map<Integer, String>) req.getSession().getAttribute("vTees");
				if (ptMap == null) ptMap = new HashMap<Integer, String>();

				/* how many t-shirts are rated */
				int progress = ptMap.size();
				if (progress > maxProgress)
				{
					String out = "<script>alert('Thanks for your cooperation. This study is finished. ');</script>";
					resp.setHeader("Refresh", "1; URL=./userStudy");
					resp.getOutputStream().write(out.getBytes());

					return;
				}

				String next = req.getParameter("survey");
				if ((progress == 0) || (next != null && next.equals("next")))
				{
					/* prepare next survey t-shirt */
					List<Tee80sRating> rs = dao.queryAllRating(userId);

					boolean found = false;
					while (true)
					{
						found = false;
						int index = RandomUtils.nextInt(ts.size());
						Tee80s t = ts.get(index);
						for (Tee80sRating r : rs)
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
					ptMap.put(progress, teeId);
					req.getSession().setAttribute("progress", progress);
					req.getSession().setAttribute("vTees", ptMap); // visited
																	// tees
					req.getSession().setAttribute("maxProgress", maxProgress);

				} else
				{
					teeId = ptMap.get(progress);
				}
			}

			String page = req.getParameter("page");
			int p = (page == null ? 1 : Integer.parseInt(page));
			req.setAttribute("page", p);

			req.setAttribute("tee", dao.queryTee80s(teeId));
			req.setAttribute("rating", dao.queryRating(userId, teeId));
			req.setAttribute("reviews", dao.queryReviews(teeId, p, pageSize));

			req.getRequestDispatcher("t-shirt.jsp").forward(req, resp);

		} else if ("rating".equals(action))
		{
			float rating = Float.parseFloat(req.getParameter("rating"));
			String comments = req.getParameter("comments");

			Tee80sRating r = new Tee80sRating();
			r.setUserId(userId);
			r.setTeeId(teeId);
			r.setRating(rating);
			r.setComments(comments);
			r.setrDate(new Date(System.currentTimeMillis()));

			OutputStream os = resp.getOutputStream();
			Tee80sRating tr = dao.queryRating(r.getUserId(), r.getTeeId());
			if (tr != null)
			{
				dao.update(r);
				os.write(("Thanks. Your rating " + rating + " and comments are updated.").getBytes());
			} else
			{
				dao.insert(r);
				os.write(("Thanks. Your rating " + rating + " and comments are saved.").getBytes());
			}
			os.close();
		} else if ("user".equals(action))
		{
			User u = new User();
			u.setUserId(req.getParameter("userId"));
			u.setGender(req.getParameter("gender"));
			String job = req.getParameter("job");
			if (job != null && job.equals("student"))
				job += "::" + req.getParameter("job1");
			else if (job != null && job.equals("staff")) job += "::" + req.getParameter("job2");
			u.setJob(job);
			u.setAge(req.getParameter("age"));
			u.setEducation(req.getParameter("education"));
			u.setShoppingExperience(req.getParameter("shoppingExperience"));
			u.setVrExperience(req.getParameter("vrExperience"));
			u.setcDate(new Date(System.currentTimeMillis()));

			if (dao.queryUser(u.getUserId()) == null)
			{
				dao.insert(u);

				req.getSession().setAttribute("userId", u.getUserId());
				resp.sendRedirect("./userStudy?action=info");
			} else
			{
				String error = "* The name '" + u.getUserId() + "' has been used.";
				req.setAttribute("error", error);
				req.setAttribute("user", u);

				RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
				rd.forward(req, resp);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

}
