package hu.gwsystems.dnsman.servlets;

import hu.gwsystems.dnsman.PMgr;
import hu.gwsystems.dnsman.entity.DnsDomain;
import hu.gwsystems.dnsman.entity.Users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterDomainServlet extends HttpServlet {
	private static final long serialVersionUID = -3010686334166281905L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String domain = req.getParameter( "domain" ).toLowerCase();
		
		EntityManager em = PMgr.getInstance().createEntityManager();
		
		
		// Domain ketszeri felvetelenek tiltasa...
		try {
			Query query = em.createQuery( "select count( d ) from DnsDomain d where d.domainName = ?0" );
			query.setParameter( 0, domain );
			
			if ( (Integer)query.getSingleResult() != 0 ) {
				resp.sendRedirect( "registerDomain.jsp?success=0&error=duplicate&domain=" + domain );
				em.close();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "Empty set?" );
		}
		
		DnsDomain dd = new DnsDomain();
		dd.setDomainName( domain );
		dd.setType( "MASTER" );
		dd.setUserId( ((Users)em.createQuery( "select u from Users u" ).getSingleResult()).getId() );
		
		try {
			em.persist( dd );
			resp.sendRedirect( "registerDomain.jsp?success=1&domain=" + domain );
		} catch (Exception e) {
			resp.sendError( 500 );
			resp.getWriter().println( e.getMessage() );
		} finally {
			em.close();
		}
	}
}
