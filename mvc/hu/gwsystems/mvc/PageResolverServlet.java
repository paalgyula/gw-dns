package hu.gwsystems.mvc;

import hu.gwsystems.mvc.annotations.Controller;
import hu.gwsystems.mvc.annotations.RequestMapping;
import hu.gwsystems.mvc.mav.ModelAndView;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageResolverServlet extends HttpServlet {
	private static final long serialVersionUID = -6912983092822406094L;
	
	private List<AbstractController> controllers = new ArrayList<AbstractController>();	
	
	public void handleRequest( HttpServletRequest req, HttpServletResponse resp, RequestMethod requestMethod ) throws IOException {
		controllers = MVCContextListener.getControllers();
		
		Iterator<AbstractController> itr = controllers.iterator();
		while ( itr.hasNext() ) {
			AbstractController at = itr.next();
			
			System.out.println( at.getClass().getName() );
			for( Annotation ann : at.getClass().getAnnotations() ) {
				System.out.println( "- " + ann.getClass().getName() );
			}
			
			if ( at.getClass().getAnnotation( Controller.class ) != null ) {
				System.out.println( "Methods:" );
				Method[] classMethods = at.getClass().getMethods();
				
				for( Method method : classMethods ) {
					Annotation rma = method.getAnnotation( RequestMapping.class );
					if( rma != null ) {
						RequestMapping ann = (RequestMapping)rma;
						boolean handled = false;
						
						if ( req.getRequestURI().startsWith( ann.pattern() ) ) {
							for( RequestMethod mt : ann.method() ) {
								if ( mt.equals( requestMethod ) && !handled ) {
									try {
										at.setRequest( req );
										at.setRequestMethod( requestMethod );
										MVCContextListener.getViewResolver().resolve( (ModelAndView)method.invoke( at, new Object[]{} ), resp );
										handled = true;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							
							if (!handled)
								resp.sendError( 405 );
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.GET );
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.POST );
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.DELETE );
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.HEAD );
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.OPTIONS );
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.PUT );
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleRequest( req, resp, RequestMethod.TRACE );
	}
}
