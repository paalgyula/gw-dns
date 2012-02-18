package hu.gwsystems.dnsman.mav;

import java.util.Map;

public class ModelAndView {
	private Map<String, Object> model;
	private String view;
	
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
