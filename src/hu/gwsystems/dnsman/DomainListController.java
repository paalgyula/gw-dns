package hu.gwsystems.dnsman;

import javax.persistence.PersistenceUnit;

import com.google.appengine.api.datastore.Email;

import hu.gwsystems.mvc.AbstractController;
import hu.gwsystems.mvc.annotations.Controller;
import hu.gwsystems.mvc.annotations.RequestMapping;
import hu.gwsystems.mvc.mav.ModelAndView;

@Controller("DomainList")
public class DomainListController extends AbstractController {
	
	@RequestMapping(pattern="/hopsz.dns")
	public ModelAndView domainList() {
		return new ModelAndView( "list", "domains", em.createQuery( "select dd from DnsDomain dd" ).getResultList() );
	}
}
