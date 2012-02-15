package hu.gwsystems.dnsman;

import hu.gwsystems.dnsman.entity.DnsDomain;
import hu.gwsystems.dnsman.entity.Users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class testServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		EntityManager em = PMgr.getInstance().createEntityManager();
		
		DnsDomain dd = new DnsDomain();
		dd.setDomainName( "gw-systems.com" );
		dd.setMaster( "" );
		dd.setLastCheck( 0L );
		dd.setNotifiedSerial( null );
		dd.setType( "MASTER" );
		dd.setUserId( ((Users)em.createQuery( "select u from Users u" ).getSingleResult()).getId() );
		
		em.persist( dd );
		em.close();
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
