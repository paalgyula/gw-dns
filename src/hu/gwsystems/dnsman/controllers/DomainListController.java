package hu.gwsystems.dnsman.controllers;

import hu.gwsystems.mvc.AbstractController;
import hu.gwsystems.mvc.annotations.Controller;
import hu.gwsystems.mvc.annotations.RequestMapping;
import hu.gwsystems.mvc.mav.ModelAndView;

@Controller("DomainList")
public class DomainListController extends AbstractController {
	
	@RequestMapping(pattern="/listDomains.dns")
	public ModelAndView domainList() {
		return new ModelAndView( "list", "domains", emf.createEntityManager().createQuery( "select dd from DnsDomain dd" ).getResultList() );
	}
	
	@RequestMapping(pattern="/listRecords.dns")
	public ModelAndView listRecords() {
		return new ModelAndView( "list", "domains", emf.createEntityManager().createQuery( "select dd from DnsDomain dd" ).getResultList() );
	}
}
