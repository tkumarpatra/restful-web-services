package com.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorld {
	
	@Autowired
	private MessageSource aMessageSource;

//	// GET method
//	// URI
//	@GetMapping(path = "/")
//	public String helloLocalHost() {
//		return "Hello from Local Host 8080 !!";
//	}

	// GET method
	// URI /Hello-World
	@RequestMapping(method = RequestMethod.GET, path = "/Hello-World")
	public String helloWorld() {
		return "Hello Tanmay !!";
	}

	// GET method
	// URI /Hello-World-Bean
	@GetMapping(path = "/Hello-World-Bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World from Bean");
	}
	
	@GetMapping(path = "/Hello-World-Bean/pathVariable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World from " + name);
	}
	
	/*@GetMapping(path = "/Hello-World-Bean/i18n")
	public String helloWorldBeani18n(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return aMessageSource.getMessage("good.morning.message", null, locale);
	}*/
	
	@GetMapping(path = "/Hello-World-Bean/i18n")
	public String helloWorldBeani18n() {
		return aMessageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
