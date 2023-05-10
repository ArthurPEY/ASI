  package com.sp.controller;
  
  import com.sp.model.*;
import com.sp.service.MarketService;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.ModelAttribute;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  
  @Controller // AND NOT @RestController
  public class RequestCrt {
	  
	  @Autowired
	  HeroDao HeroDao;

	  @Autowired
	  MarketService MarketService;

  	@Value("${welcome.message}")
  	private String message;
  
  	private static String messageLocal="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
  
  	
 	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
    public String Index(Model model) {
 		model.addAttribute("myHeroList", HeroDao.getMyHeroList());
      return "card";
 	}
  	
 	@RequestMapping(value = { "/card"}, method = RequestMethod.GET)
    public String card(Model model) {
      return "card";
 	}
 	
 	
 	@RequestMapping(value = { "/form"}, method = RequestMethod.GET)
    public String addHero(Model model) {
    	HeroFormDTO form = new HeroFormDTO();
    	model.addAttribute("form", form);
    	return "form";
 	}
 	
 	@RequestMapping(value = { "/form"}, method = RequestMethod.POST)
    public String addHero(Model model, @ModelAttribute("form") HeroFormDTO form) {
	  Hero p=HeroDao.addHero(form.getOwner(),form.getFamily(),form.getName(),form.getDescription(),form.getAffinity(),form.getImgUrl(),form.getHp(),form.getEnergy(),form.getAttack(),form.getDefence());
	  model.addAttribute("myHeroList", HeroDao.getMyHeroList());
      return "card";
 	}
 	
 	@RequestMapping(value = { "/inscription"}, method = RequestMethod.GET)
    public String inscription(Model model) {
    	return "inscription";
 	}
 	
 	@RequestMapping(value = { "/market"}, method = RequestMethod.GET)
    public String market(Model model) {
 		/*MarketService.createBaseCards();*/
    	return "market";
 	}
 	
 	
  	/*
  	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
  	public String index(Model model) {
    
  		model.addAttribute("message", message);
  		model.addAttribute("messageLocal", messageLocal);
  
  		return "index";
  		
  	}
  	
  	@RequestMapping(value = { "/view"}, method = RequestMethod.GET)
	public String view(Model model) {

		model.addAttribute("myPoney",poneyDao.getRandomPoney() );

		return "poneyView";
	}

  	
    @RequestMapping(value = { "/addPoney"}, method = RequestMethod.GET)
    public String addponey(Model model) {
    	PoneyFormDTO poneyForm = new PoneyFormDTO();
    	model.addAttribute("poneyForm", poneyForm);
    	return "poneyForm";
    }

	 @RequestMapping(value = { "/addPoney"}, method = RequestMethod.POST)
	    public String addponey(Model model, @ModelAttribute("poneyForm") PoneyFormDTO poneyForm) {
		  Poney p=poneyDao.addPoney(poneyForm.getName(),poneyForm.getColor(),poneyForm.getSuperPower(),poneyForm.getImgUrl());
	      model.addAttribute("myPoney",p );
	      return "poneyView";
	 }
	   @RequestMapping(value = { "/list"}, method = RequestMethod.GET)
	    public String viewList(Model model) {
	  	  model.addAttribute("poneyList",poneyDao.getPoneyList() );
	  	  return "poneyViewList";
	    }
*/
	 
}