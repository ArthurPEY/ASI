package com.sp.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.controller.Hero;
import com.sp.service.HeroService;

@RestController
public class HeroRestCrt {
	@Autowired
	HeroService hService;
	
	public List<String> Parse(String listStr){
		listStr = listStr.replace("[", "").replace("]", "");
		listStr = listStr.replace("\"", "");
		String[] split = listStr.split(",");
		List<String> list = Arrays.asList(split);
		
		return list;
	}

	
    @RequestMapping(method=RequestMethod.POST,value="/addhero")
    public void addHero(@RequestBody Hero hero) {
        hService.addHero(hero);
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/gethero/{id}")
    public Hero getHero(@PathVariable String id) {
        Hero h=hService.getHero(Integer.valueOf(id));
        return h;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/updateenergy")
    public void updateEnergy(@RequestBody String cardId_energy){
    	List<String> list = Parse(cardId_energy);
    	int cardId = Integer.parseInt(list.get(0));
    	int energy = Integer.parseInt(list.get(1));
        hService.updateEnergy(cardId,energy);
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/getenergy/{cardId}")
    public int getenergy(@PathVariable String cardId){
       int energy = hService.getEnergy(Integer.valueOf(cardId));
       return energy;
    }
    
    
    @RequestMapping(method=RequestMethod.POST,value="/delhero/{id}")
    public void delHero(@PathVariable String id) {
        hService.delHero(Integer.valueOf(id));
    }
	
	@RequestMapping(method=RequestMethod.POST,value="/requestcard/{id}")
	public List<Hero> getCardUser(@PathVariable int id){
		List<Hero> h=hService.getCardUser(id);
		return h;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/case")
    public void casewin(@RequestBody String userId_index){
    	List<String> list = Parse(userId_index);
    	int userId = Integer.parseInt(list.get(0));
    	int index = Integer.parseInt(list.get(1));
 
		hService.casewin(userId,index);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/basehero")
    public List<Hero> basehero(){
		List<Hero> allBaseHero = hService.basehero();
		return allBaseHero;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addbasehero")
    public void addbasehero(@RequestBody String userId_index){
    	List<String> list = Parse(userId_index);
    	int userId = Integer.parseInt(list.get(0));
    	int index = Integer.parseInt(list.get(1));
		hService.addbasehero(userId, index);
	}
		
}