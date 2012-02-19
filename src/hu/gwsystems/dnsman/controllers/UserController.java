package hu.gwsystems.dnsman.controllers;

import hu.gwsystems.mvc.AbstractController;
import hu.gwsystems.mvc.annotations.Controller;
import hu.gwsystems.mvc.annotations.RequestMapping;
import hu.gwsystems.mvc.mav.ModelAndView;

@Controller("UserController")
public class UserController extends AbstractController {
	
	@RequestMapping(pattern="/loginUser.dns")
	public ModelAndView loginUser() {
		return new ModelAndView( "loginUser" );
	}
	
	@RequestMapping(pattern="/registerUser.dns")
	public ModelAndView registerUser() {
		return new ModelAndView( "registerUser" );
	}
}
