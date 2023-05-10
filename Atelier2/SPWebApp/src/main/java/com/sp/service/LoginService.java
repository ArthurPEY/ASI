package com.sp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sp.model.User;
import com.sp.model.Hero;
import com.sp.repository.UserRepository;

@Service
public class LoginService {
	@Autowired
	UserRepository uRepository;
	
	@Autowired
	HeroService hService;
	
	public void addUser(User u) {
		List<User> uOpt = uRepository.findByName(u.getName());
		if (uOpt.isEmpty()){
			User createdUser=uRepository.save(u);
			this.addBaseCards(u.getId());
			System.out.println(createdUser);
		}
		else {
			System.out.println("Utilisateur deja existant");
		}
		

	}
	
	public User getUser(int id) {
		Optional<User> uOpt = uRepository.findById(id);
		if (uOpt.isPresent()) {
			return uOpt.get();
		}else {
			return null;
		}
	}
	
	
	public int login(List<String> info) {
		String username = info.get(0);
		String password = info.get(1);
		List<User> uOpt = uRepository.findByName(username);
		try {
			if (uOpt.get(0) instanceof User) {
				if (uOpt.get(0).getPassword().equals(password)) {
					return uOpt.get(0).getId();
				}
			}
			return -1;
		}
		catch(Exception e){
			return -1;
		}
	}

	public int getMoneyUser(Integer id) {
		Optional<User> uOpt = uRepository.findById(id);
		User u = uOpt.get();
		int money = u.getMoney();
		return money;
	}
	
	public void addBaseCards(int id) {
		Hero IronMan = new Hero(0,id, "Hero","Iron Man","Iron Man","Lumière","https://e7.pngegg.com/pngimages/355/993/png-clipart-iron-man-captain-america-chibi-iron-man-marvel-avengers-assemble-superhero.png",
				90,100,82,80);
		
		Hero Hulk = new Hero(0,id,"Hero","Hulk","L'homme le plus fort du monde","Terre","https://files.cults3d.com/uploaders/13774998/illustration-file/cac07153-06e8-46c1-86c8-2466abfb3cd0/chibi_Hulk.png" ,
				80,80,100,60);
	    
		Hero CptAmerica = new Hero(0,id,"Hero","Captain America","Captain America","Terre","https://m.media-amazon.com/images/I/71ntUhYfPiL._AC_UY679_.jpg" ,
				92,90,75,100);
		
		Hero Thanos = new Hero(0,id,"Vilain","Thanos","Le Titan fou","Ombre", "https://www.kindpng.com/picc/m/111-1118680_thanos-kirby-hd-png-download.png",
				100 ,100,100,100);
		
		Hero SpiderMan = new Hero(0,id,"Hero","Spider-Man","L'Homme araignée","Air", "https://m.media-amazon.com/images/I/51tEXq0ZDcL._AC_UF1000,1000_QL80_.jpg",
				72 ,100,92,70);
		
		hService.addHero(IronMan);
		hService.addHero(Hulk);
		hService.addHero(CptAmerica);
		hService.addHero(Thanos);
		hService.addHero(SpiderMan);

	}
	
	
	
	
	

}
