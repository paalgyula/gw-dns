package hu.gwsystems.mvc.annotations;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	public enum RequestType {
		POST,
		GET,
		DELETE,
		OPTION,
		HEAD
	}
	
	String pattern();
	RequestType[] method() default {
		RequestType.GET,
		RequestType.POST,
		RequestType.HEAD,
		RequestType.DELETE,
		RequestType.OPTION
	};
}
