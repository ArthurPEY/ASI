package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sp.model.Hero;
import com.sp.repository.MarketRepository;


@Service
public class MarketService {
	@Autowired
	MarketRepository mRepository;


	

	public List<Hero> getCardSell() {
		List<Hero> hOpt = mRepository.findByOnSell(true);
		System.out.println("Recuperation des cartes en ventes");
		System.out.println(hOpt);
		return hOpt;
	}
	
	public void buyCard(int newOwner,int cardId){
		RestTemplate restTemplate = new RestTemplate();
		
		Optional<Hero> hOpt = mRepository.findById(cardId);
		Hero h = hOpt.get();
		
		System.out.println(String.format("Achat de la carte %d par l'acheteur %d",cardId,newOwner));
		
		String uriBuyerGet = String.format("http://localhost:80/user/getmoney/%d",newOwner);
		int buyerMoney = restTemplate.postForObject(uriBuyerGet, int.class, int.class);
		
		System.out.println("Recuperation de l'argent de l'acheteur");
		
		int cardPrice = h.getPrice();
		
		if (buyerMoney >= cardPrice) {
			
			String uriBuyerSet = "http://localhost:80/user/setmoney";
			String BodyBuyer = String.format("[%d,%d]",newOwner,-cardPrice);
			restTemplate.postForObject(uriBuyerSet,BodyBuyer,String.class); // Payement de la carte
			
			System.out.println("MAJ de l'argent acheteur");
			
			String uriSellerSet = "http://localhost:80/user/setmoney";
			String BodySeller = String.format("[%d,%d]",h.getOwner(),+cardPrice);
			restTemplate.postForObject(uriSellerSet,BodySeller,String.class);  // Reception du payement par le vendeur
			
			System.out.println("MAJ de l'argent vendeur");
			
			h.setOwner(newOwner); // Changement de proprietaire de la carte
			h.setOnSell(false); // Retire la carte du market
			
			
			mRepository.deleteById(cardId);
			h.setId(null);
			
			String uri = "http://localhost:80/hero/addhero";
			restTemplate.postForObject(uri, h, Hero.class);
			
			
			System.out.println(String.format("Carte %d vendu !",cardId));
		}
		else {
			System.out.println("Achat impossible !");
		}
	}

	public void sellCard(int price,int cardId){
		RestTemplate restTemplate = new RestTemplate();
		
		String uriGetHero = String.format("http://localhost:80/hero/gethero/%d",cardId);
		Hero heroToSell = restTemplate.postForObject(uriGetHero, int.class, Hero.class);

		heroToSell.setPrice(price);
		heroToSell.setOnSell(true);
		mRepository.save(heroToSell);
		
		String uriDelHero = String.format("http://localhost:80/hero/delhero/%d",cardId);
		restTemplate.postForObject(uriDelHero, int.class, Hero.class);
		
		System.out.println(String.format("Carte %d a vendre au prix de %d",cardId,price));
	}

}
