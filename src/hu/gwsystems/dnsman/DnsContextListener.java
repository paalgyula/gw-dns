package hu.gwsystems.dnsman;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DnsContextListener implements ServletContextListener {

	private static ServletContext servletContext;
	
	public static ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		servletContext = e.getServletContext();
		PMgr.getInstance();
	}

}
