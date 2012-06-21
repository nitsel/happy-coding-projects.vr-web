package org.felix.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.felix.db.Tee80s;
import org.felix.db.Tee80sDao;
import org.felix.db.Tee80sRating;
import org.felix.db.Tee80sReview;

public class TShirtServlet extends HttpServlet
{
	private final static Tee80sDao dao = new Tee80sDao();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if (action == null) action = "info";

		String teeId = req.getParameter("teeId");
		if (teeId == null) teeId = "TREK025";

		String userId = (String) req.getSession().getAttribute("userId");
		if (userId == null)
		{
			userId = "guoguibing";
			req.getSession().setAttribute("userId", userId);
		}

		if ("info".equals(action))
		{
			String page = req.getParameter("page");
			int p = 1;

			if (page != null) p = Integer.parseInt(page);
			req.setAttribute("page", p);

			Tee80s t = dao.queryTee80s(teeId);
			req.setAttribute("tee", t);
			
			Tee80sRating r = dao.queryTee80sRating(userId, teeId);
			req.setAttribute("rating", r);

			int pageSize = 10;
			List<Tee80sReview> rs = dao.queryReviews(teeId, p, pageSize);
			req.setAttribute("reviews", rs);

			RequestDispatcher rd = req.getRequestDispatcher("t-shirt.jsp");
			rd.forward(req, resp);

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
			Tee80sRating tr = dao.queryTee80sRating(r.getUserId(), r.getTeeId());
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
		}
	}

}
