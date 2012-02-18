package hu.gwsystems.dnsman.servlets;

import hu.gwsystems.dnsman.PMgr;
import hu.gwsystems.dnsman.entity.DnsDomain;
import hu.gwsystems.mvc.MVCContextListener;
import hu.gwsystems.mvc.mav.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DomainListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7300083777061105640L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = PMgr.getInstance().createEntityManager();
		List<DnsDomain> ddList = em.createQuery( "select d from DnsDomain d" ).getResultList();
		
		/* Create a data-model */
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("domains", ddList );
        
        MVCContextListener.getViewResolver().resolve( new ModelAndView( root, "list" ) , resp );
	}
}
