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
	private final static Tee80sDao	dao					= new Tee80sDao();

	private static final long		serialVersionUID	= 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String action = req.getParameter("action");
		if (action == null) action = "info";

		if ("info".equals(action))
		{
			String id = req.getParameter("id");
			String page = req.getParameter("page");
			int p = 0;
			if (id == null) id = "TREK025";
			if (page != null) p = Integer.parseInt(page);

			Tee80s t = dao.queryTee80s(id);
			req.setAttribute("tee", t);
			
			List<Tee80sReview> rs = dao.queryReviews(id);
			req.setAttribute("reviews", rs);
			System.out.println("rs: " + (rs == null) + ", rs: " + rs.isEmpty() + ", size: " + rs.size());

			RequestDispatcher rd = req.getRequestDispatcher("t-shirt.jsp");
			rd.forward(req, resp);

		} else if ("rating".equals(action))
		{
			float rating = Float.parseFloat(req.getParameter("rating"));
			String userId = req.getParameter("userId");
			String teeId = req.getParameter("teeId");

			Tee80sRating r = new Tee80sRating();
			r.setUserId(userId);
			r.setTeeId(teeId);
			r.setRating(rating);
			r.setrDate(new Date(System.currentTimeMillis()));

			OutputStream os = resp.getOutputStream();
			Tee80sRating tr = dao.queryTee80sRating(r.getUserId(), r.getTeeId());
			if (tr != null)
			{
				dao.update(r);
				os.write(("Thanks. Your rating " + rating + " is updated.").getBytes());
			} else
			{
				dao.insert(r);
				os.write(("Thanks. Your rating " + rating + " is saved.").getBytes());
			}
			os.close();
		}
	}

}
