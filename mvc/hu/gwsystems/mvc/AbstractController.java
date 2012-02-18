package hu.gwsystems.mvc;

import hu.gwsystems.dnsman.PMgr;

import javax.persistence.EntityManager;


public abstract class AbstractController {
	protected EntityManager em;
	
	public AbstractController() {
		em = PMgr.getInstance().createEntityManager();
	}
}
