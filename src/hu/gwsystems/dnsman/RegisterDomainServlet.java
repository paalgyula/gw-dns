package hu.gwsystems.dnsman;

import hu.gwsystems.dnsman.entity.DnsDomain;
import hu.gwsystems.dnsman.entity.Users;

import java.io.IOException;

import javax.persistence.EntityManager;
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
		if ( em.createQuery( "select d from DnsDomain d where d.domainName = :NAME" ).setParameter( ":NAME", domain ).getResultList().size() != 0 ) {
			resp.sendRedirect( "registerDomain.jsp?success=0&error=duplicate&domain=" + domain );
			return;
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
