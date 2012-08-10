package org.felix.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent e)
	{
		ServletContext ctx = e.getServletContext();
		String prefix = ctx.getRealPath("/");
		String file = "WEB-INF" + System.getProperty("file.separator") + "log4j.properties";

		PropertyConfigurator.configure(prefix + file);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{}

}
