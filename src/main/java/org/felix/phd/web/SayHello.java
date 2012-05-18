package org.felix.phd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SayHello extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Something");
		String method = request.getMethod();
		if ("submitRating".equalsIgnoreCase(method))
		{
			submitRating(request, response);
		}
	}

	private void submitRating(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		String rating = (String) request.getAttribute("r");
		System.out.println("lkajsdlfka");
		PrintWriter out = response.getWriter();
		out.write("You rating is " + rating);
		out.close();
	}
}
