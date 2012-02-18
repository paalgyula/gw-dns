package hu.gwsystems.mvc.mav;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private Map<String, Object> model;
	private String view;
	
	public ModelAndView( String view, String parameterName, Object parameterValue ) {
		this.view = view;
		this.model = new HashMap<String, Object>();
		this.model.put( parameterName, parameterValue );
	}
	
	public ModelAndView( String view ) {
		this.view = view;
		this.model = new HashMap<String, Object>();
	}
	
	public ModelAndView( Map<String, Object> model, String view ) {
		this.model = model;
		this.view = view;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public String getView() {
		return view;
	}
}
