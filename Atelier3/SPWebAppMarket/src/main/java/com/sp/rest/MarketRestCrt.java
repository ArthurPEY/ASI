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
import com.sp.service.MarketService;


@RestController
public class MarketRestCrt {
	@Autowired
	MarketService mService;
	
    
	public List<String> Parse(String listStr){
		listStr = listStr.replace("[", "").replace("]", "");
		listStr = listStr.replace("\"", "");
		String[] split = listStr.split(",");
		List<String> list = Arrays.asList(split);
		
		return list;
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/requestcardsell")
	public List<Hero> getCardSell(){
		List<Hero> h=mService.getCardSell();
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
		
		mService.buyCard(newOwner,cardId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/sell")
	public void sellCard(@RequestBody String cardId_price){
		List<String> list = Parse(cardId_price);
		int cardId = Integer.parseInt(list.get(0));
		int price = Integer.parseInt(list.get(1));

		mService.sellCard(price,cardId);
	}
	

	

	
}