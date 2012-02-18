package hu.gwsystems.mvc;

import hu.gwsystems.dnsman.DomainListController;
import hu.gwsystems.mvc.annotations.Controller;
import hu.gwsystems.mvc.annotations.RequestMapping;
import hu.gwsystems.mvc.mav.ModelAndView;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.generics.scope.MethodScope;

public class PageResolverServlet extends HttpServlet {
	private static final long serialVersionUID = -6912983092822406094L;
	
	private List<AbstractController> controllers = new ArrayList<AbstractController>();
	public void addController( AbstractController controller ) {
		this.controllers.add( controller );
	}
	
	public PageResolverServlet() {
		addController( new DomainListController() );
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Iterator<AbstractController> itr = controllers.iterator();
		while ( itr.hasNext() ) {
			AbstractController at = itr.next();
			
			System.out.println( at.getClass().getName() );
			for( Annotation ann : at.getClass().getAnnotations() ) {
				System.out.println( "- " + ann.getClass().getName() );
			}
			
			if ( at.getClass().getAnnotation( Controller.class ) != null ) {
				System.out.println( "Metodusok:" );
				Method[] classMethods = at.getClass().getMethods();
				for( Method method : classMethods ) {
					Annotation rma = method.getAnnotation( RequestMapping.class );
					if( rma != null ) {
						RequestMapping ann = (RequestMapping)rma;
						System.out.println( " - " + method.getName() + " Mapping: \"" + ann.pattern() + "\"" );
						System.out.println( req.getRequestURI() );
						if ( req.getRequestURI().startsWith( ann.pattern() ) ) {
							try {
								MVCContextListener.getViewResolver().resolve( (ModelAndView)method.invoke( at, new Object[]{} ), resp );
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
