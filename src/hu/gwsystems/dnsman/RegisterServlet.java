package hu.gwsystems.dnsman;

import hu.gwsystems.dnsman.entity.Users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -9221484063796606079L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter( "username" );
		String password = req.getParameter( "password" );
		
		EntityManager em = PMgr.getInstance().createEntityManager();
		
		int users = em.createQuery( "select u from " + Users.class.getName() + " u where u.email = :email" ).setParameter( "email", email ).getResultList().size();
		if ( users != 0 ) {
			resp.sendRedirect( "regerror.jsp?error=1&username=" + email );
		} else {
			Users dnsUser = new Users();
			dnsUser.setEmail( email );
			dnsUser.setPassword( password );
			
			try {
				em.persist( dnsUser );
			} catch (Exception e) {
				System.out.println( "Nem sikerult menteni!" );
			} finally {
				em.close();
			}
		}
	}
}
