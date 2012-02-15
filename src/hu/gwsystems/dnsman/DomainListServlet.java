package hu.gwsystems.dnsman;

import hu.gwsystems.dnsman.entity.DnsDomain;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainListServlet extends HttpServlet {
	private static final long serialVersionUID = -4583906174321725426L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = PMgr.getInstance().createEntityManager();
		List<DnsDomain> ddList = em.createQuery( "select dd from DnsDomain dd" ).getResultList();
		
		ObjectOutputStream ous = new ObjectOutputStream( resp.getOutputStream() );
		ous.writeObject( ddList.toArray() );
		
		em.close();
	}
}
