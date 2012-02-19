package hu.gwsystems.mvc;

import hu.gwsystems.mvc.mav.DefaultViewResolver;
import hu.gwsystems.mvc.mav.ViewResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MVCContextListener implements ServletContextListener {

	private static ServletContext servletContext;
	private static ViewResolver viewResolver;
	private static List<AbstractController> controllers;
	
	@Override
	public void contextDestroyed(ServletContextEvent e) {}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		servletContext = e.getServletContext();
		viewResolver = new DefaultViewResolver( servletContext );
		controllers = new ArrayList<AbstractController>();
		
		Properties props = new Properties();
		try {
			props.load( getClass().getResourceAsStream( "/views.properties" ) );
		} catch (IOException ex) {
			Logger.getLogger( getClass().getName() ).severe( "The views.properties file is missing from the config. The controllers will be ignored!" );
		}
		
		String packageName = (String)props.get( "controllers.package" );
		File file = new File( Thread.currentThread().getContextClassLoader().getResource( packageName.replace( '.', '/' ) ).getFile() );
		
		String[] fileList = file.list();
		
		for ( String fileName : fileList ) {
			System.out.println( "Controller found: " + fileName );
			if ( fileName.endsWith( ".class" ) ) {
				fileName = fileName.replaceAll( "\\.class", "" );
				try {
					AbstractController cls = (AbstractController)Class.forName( packageName + "." + fileName ).newInstance();
					controllers.add( cls );
				} catch (ClassNotFoundException ex) {}
				catch (InstantiationException ex) {}
				catch (IllegalAccessException ex) {}
			}
		}
	}

	public static ViewResolver getViewResolver() {
		return viewResolver;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	public static List<AbstractController> getControllers() {
		return controllers;
	}
}
