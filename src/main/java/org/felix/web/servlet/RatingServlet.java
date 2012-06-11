package org.felix.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RatingServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		if ("submit".equalsIgnoreCase(action))
		{
			submitRating(request, response);
		}
	}

	private void submitRating(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String rating = request.getParameter("rating");
		PrintWriter out = response.getWriter();
		out.write("Thanks. Your rating " + rating + " is saved.");
		out.close();
	}
}
