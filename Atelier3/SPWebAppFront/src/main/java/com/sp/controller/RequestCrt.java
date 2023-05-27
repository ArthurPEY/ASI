  package com.sp.controller;
  


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.model.User;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



  @Controller // AND NOT @RestController
  public class RequestCrt {
	  
	  @Configuration
	  public class WebConfig implements WebMvcConfigurer {
	      @Override
	      public void addResourceHandlers(ResourceHandlerRegistry registry) {
	          registry.addResourceHandler("/**")
	                  .addResourceLocations("classpath:/static/");
	      }
	  }
  	
 	@RequestMapping(value = { "/collection"}, method = RequestMethod.GET)
    public String card(Model model) {
      return "card";
 	}
 	
 	@RequestMapping(value = { "/generateur"}, method = RequestMethod.GET)
    public String generateur(Model model) {
      return "form";
 	}

 	
 	@RequestMapping(value = { "/inscription"}, method = RequestMethod.GET)
    public String inscription(Model model) {
    	return "inscription";
 	}
 	
 	@RequestMapping(value = { "/market"}, method = RequestMethod.GET)
    public String market(Model model) {

    	return "market";
    	}
 	
 	@RequestMapping(value = { "/room"}, method = RequestMethod.GET)
    public String room(Model model) {

    	return "lobbyfight";
    	}
 	
	@RequestMapping(value = { "/attente/{roomId}"}, method = RequestMethod.GET)
    public String attente(@PathVariable int roomId) {

    	return "waitingscreen";
    	}
	
	@RequestMapping(value = { "/selection/{roomId}"}, method = RequestMethod.GET)
    public String selection(@PathVariable int roomId) {

    	return "selection";
    	}
	
	@RequestMapping(value = { "/combat/{combatId}"}, method = RequestMethod.GET)
    public String combat(@PathVariable int combatId) {

    	return "combat";
    	}
	
	@RequestMapping(value = { "/combat/win/{id}"}, method = RequestMethod.GET)
    public String win(Model model) {

    	return "win";
    	}
	
	@RequestMapping(value = { "/combat/loose/{id}"}, method = RequestMethod.GET)
    public String loose(Model model) {

    	return "loose";
    	}
	
	@RequestMapping(value = { "/case"}, method = RequestMethod.GET)
    public String caseope(Model model) {

    	return "case";
    	}
	
	@RequestMapping(value = { "/","/index"}, method = RequestMethod.GET)
    public String index(Model model) {

    	return "index";
    	}
 

    	
 	}
 	
 	