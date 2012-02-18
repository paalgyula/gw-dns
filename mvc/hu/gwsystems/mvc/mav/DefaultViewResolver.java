package hu.gwsystems.mvc.mav;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DefaultViewResolver implements ViewResolver {

	private Configuration config;
	private String encoding = "UTF-8";
	private String templateDir = "templates";
	private String postfix = ".ftl";
	
	public DefaultViewResolver( ServletContext sc ) {
		try {
			config = new Configuration();
			config.setDirectoryForTemplateLoading( new File( sc.getRealPath( "/WEB-INF/" + templateDir ) ) );
			config.setObjectWrapper(new DefaultObjectWrapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void resolve( ModelAndView mav, HttpServletResponse response ) throws IOException {
		if ( config == null ) {
			response.sendError( 500 );
			response.getWriter().println( "Uninitialized view resolver" );
		}
		
		Template template = config.getTemplate( mav.getView() + postfix, encoding );
		try {
			template.process( mav.getModel(), response.getWriter() );
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
