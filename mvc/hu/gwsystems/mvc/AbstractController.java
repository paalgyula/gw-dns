package hu.gwsystems.mvc;

import hu.gwsystems.dnsman.PMgr;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AbstractController {
	protected EntityManagerFactory emf;
	private HttpServletRequest req;
	
	protected HttpSession session;
	protected Map<String, Object> params;
	
	protected RequestMethod method;
	
	@SuppressWarnings("unchecked")
	public void setRequest( HttpServletRequest req ) {
		this.req = req;
		this.session = req.getSession();
		this.params = req.getParameterMap();
	}
	
	public void setRequestMethod(RequestMethod method) {
		this.method = method;
	}
	
	public AbstractController() {
		emf = PMgr.getInstance();
	}
	
	public HttpServletRequest getRequest() {
		return this.req;
	}
}
