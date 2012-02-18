package hu.gwsystems.dnsman.mav;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public interface ViewResolver {
	public void resolve( ModelAndView mav, HttpServletResponse response ) throws IOException;
}
