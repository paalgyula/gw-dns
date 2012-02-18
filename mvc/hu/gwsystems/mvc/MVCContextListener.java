package hu.gwsystems.mvc;

import hu.gwsystems.mvc.mav.DefaultViewResolver;
import hu.gwsystems.mvc.mav.ViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MVCContextListener implements ServletContextListener {

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
