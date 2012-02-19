package hu.gwsystems.mvc.annotations;

import hu.gwsystems.mvc.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
	String pattern();
	RequestMethod[] method() default {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.HEAD,
		RequestMethod.DELETE,
		RequestMethod.OPTIONS
	};
}
