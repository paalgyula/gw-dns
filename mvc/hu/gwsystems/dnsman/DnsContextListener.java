package hu.gwsystems.dnsman;

import hu.gwsystems.dnsman.mav.DefaultViewResolver;
import hu.gwsystems.dnsman.mav.ViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DnsContextListener implements ServletContextListener {

	private static ServletContext servletContext;
	private static ViewResolver viewResolver;
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		servletContext = e.getServletContext();
		viewResolver = new DefaultViewResolver( servletContext );
	}

	public static ViewResolver getViewResolver() {
		return viewResolver;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
}
