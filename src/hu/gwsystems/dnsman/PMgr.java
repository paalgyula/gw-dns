package hu.gwsystems.dnsman;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PMgr {

	public final static EntityManagerFactory instance = Persistence.createEntityManagerFactory( "gw-dns" );
	
	public static EntityManagerFactory getInstance() {
		return instance;
	}
	
	private PMgr() {}
}
