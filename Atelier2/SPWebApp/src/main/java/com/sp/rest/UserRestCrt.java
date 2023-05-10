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

import com.sp.model.Hero;
import com.sp.model.User;
import com.sp.service.LoginService;
import com.sp.service.MarketService;
import com.sp.service.HeroService;

@RestController
public class UserRestCrt {
	@Autowired
	LoginService lService;
	@Autowired
	HeroService hService;
	@Autowired
	MarketService mService;
	
    
    @RequestMapping(method=RequestMethod.POST,value="/hero")
    public void addHero(@RequestBody Hero hero) {
    	System.out.println(hero);
        hService.addHero(hero);
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/hero/{id}")
    public Hero getHero(@PathVariable String id) {
        Hero h=hService.getHero(Integer.valueOf(id));
        return h;
    }
	
	
	@RequestMapping(method=RequestMethod.POST,value="/adduser")
	public void addUser(@RequestBody User user) {
		System.out.println(user);
		lService.addUser(user);
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/user/{id}")
	public User getUser(@PathVariable String id) {
		User l=lService.getUser(Integer.valueOf(id));
		return l;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public int login(@RequestBody String user_pass) {
		user_pass = user_pass.replace("[", "").replace("]", "");
		user_pass = user_pass.replace("\"", "");
		String[] split = user_pass.split(",");
		List<String> list = Arrays.asList(split);
		System.out.println(list);
		System.out.println(list.get(0));
		int existUser = lService.login(list);
		System.out.println(existUser);
		return existUser;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/requestcard/{id}")
	public List<Hero> getCardUser(@PathVariable int id){
		System.out.println("fct");
		System.out.println(id);
		List<Hero> h=hService.getCardUser(id);
		System.out.println(h);
		return h;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/requestcardsell")
	public List<Hero> getCardSell(){
		List<Hero> h=mService.getCardSell();
		System.out.println(h);
		return h;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/buy")
	public void buyCard(@RequestBody String newOwner_cardId){
		newOwner_cardId = newOwner_cardId.replace("[", "").replace("]", "");
		newOwner_cardId = newOwner_cardId.replace("\"", "");
		String[] split = newOwner_cardId.split(",");
		List<String> list = Arrays.asList(split);
		int newOwner = Integer.parseInt(list.get(0));
		int cardId = Integer.parseInt(list.get(1));
		System.out.println(newOwner);
		System.out.println(cardId);
		mService.buyCard(newOwner,cardId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/sell")
	public void sellCard(@RequestBody String price_cardId){
		price_cardId = price_cardId.replace("[", "").replace("]", "");
		price_cardId = price_cardId.replace("\"", "");
		String[] split = price_cardId.split(",");
		List<String> list = Arrays.asList(split);
		int price = Integer.parseInt(list.get(0));
		int cardId = Integer.parseInt(list.get(1));
		System.out.println(price);
		System.out.println(cardId);
		mService.sellCard(price,cardId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/money/{id}")
	public int getMoneyUser(@PathVariable int id) {
		int money=lService.getMoneyUser(id);
		return money;
	}
	

	
}