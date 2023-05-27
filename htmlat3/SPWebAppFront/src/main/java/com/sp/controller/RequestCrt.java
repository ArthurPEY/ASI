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
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



  @Controller // AND NOT @RestController
  public class RequestCrt {
  	
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
 	

    	
 	}
 	
 	