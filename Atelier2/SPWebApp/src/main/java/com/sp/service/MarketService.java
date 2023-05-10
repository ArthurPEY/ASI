package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Hero;
import com.sp.model.User;
import com.sp.repository.HeroRepository;
import com.sp.repository.UserRepository;

@Service
public class MarketService {
	@Autowired
	HeroRepository hRepository;
	@Autowired
	UserRepository uRepository;
	

	public List<Hero> getCardSell() {
		List<Hero> hOpt = hRepository.findByOnSell(true);
		return hOpt;
	}
	
	public void buyCard(int newOwner,int cardId){
		Optional<Hero> hOpt = hRepository.findById(cardId);
		Hero h = hOpt.get();
		
		Optional<User> bOpt = uRepository.findById(newOwner);
		User buyer = bOpt.get();
		
		Optional<User> sOpt = uRepository.findById(h.getOwner());
		User seller = sOpt.get();
		
		int cardPrice = h.getPrice();
		
		if (buyer.getMoney() >= cardPrice) {
			int currentMoneyBuyer = buyer.getMoney(); 
			buyer.setMoney(currentMoneyBuyer-cardPrice); // Payement de la carte
			
			int currentMonetSeller = seller.getMoney();
			seller.setMoney(currentMonetSeller+cardPrice); // Reception du payement par le vendeur
			
			h.setOwner(newOwner); // Changement de proprietaire de la carte
			h.setOnSell(false); // Retire la carte du market
			
			hRepository.save(h); // Update de la table HeroRepository pour actualisation de la carte
			uRepository.save(buyer); // Update de la table UserRepository pour udpate du compte acheteur
			uRepository.save(seller); // Update de la table UserRepository pour udpate du compte vendeur
			
			System.out.println("Vendu");
		}
		else {
			System.out.println("Achat impossible");
		}
	}

	public void sellCard(int price,int cardId){
		Optional<Hero> hOpt = hRepository.findById(cardId);
		Hero h = hOpt.get();
		h.setPrice(price);
		h.setOnSell(true);
		hRepository.save(h);
		System.out.println("Carte a vendre");
	}

	/* Ne peut pas être utilisé avec notre foncionnement du market
	public void createBaseCards() {	
		if (hRepository.findByOwner(-999).isEmpty()) {
			Hero h1 = new Hero(-999,-999, "Hero","Iron Man","Iron Man","Lumière","https://e7.pngegg.com/pngimages/355/993/png-clipart-iron-man-captain-america-chibi-iron-man-marvel-avengers-assemble-superhero.png",
					90,100,82,80,true,999,500);
		    
			hRepository.save(h1);
		}
	}*/

}
