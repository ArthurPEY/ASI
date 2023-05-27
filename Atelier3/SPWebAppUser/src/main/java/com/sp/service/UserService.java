package com.sp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.model.Hero;
import com.sp.model.User;
import com.sp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository uRepository;
	
	
	public void addUser(User u) {
		List<User> uOpt = uRepository.findByName(u.getName());
		if (uOpt.isEmpty()){
			User createdUser=uRepository.save(u);
			this.addBaseCards(u.getId());
			System.out.println(String.format("Creation de l'utilistateur id=%d, username=%s, psw=%s",u.getId(),u.getName(),u.getPassword()));
		}
		else {
			System.out.println(String.format("Utilisateur %s deja existant",uOpt.get(0).getName()));
		}
		

	}
	
	public User getUser(int id) {
		Optional<User> uOpt = uRepository.findById(id);
		if (uOpt.isPresent()) {
			System.out.println(String.format("Recuperation de l'utilisateur id=%d (username=%s)",id,uOpt.get().getName()));
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
					System.out.println(String.format("Connexion de l'utilisateur %s",uOpt.get(0).getName()));
					return uOpt.get(0).getId();
				}
			}
			System.out.println(String.format("Echec de connexion de l'utilisateur %s",username));
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
		System.out.println(String.format("Recuperation de l'argent de l'utilisateur %s : argent = %s",u.getName(),u.getMoney()));
		return money;
	}
	
	public void setMoneyUser(Integer id,Integer change) {
		Optional<User> uOpt = uRepository.findById(id);
		User u = uOpt.get();
		int currentMoney = u.getMoney();
		u.setMoney(currentMoney+change);
		System.out.println(String.format("MAJ de l'argent de l'utilisateur %s : argent += %d",u.getName(),change));
		uRepository.save(u);
	}
	

	public void addBaseCards(int id) {
		RestTemplate restTemplate = new RestTemplate();	
		
		System.out.println(String.format("Ajout des cartes de base a l'utilisateur %d",id));
		String uriAddBaseCard = "http://127.0.0.1:80/hero/addbasehero";
		
		List<Integer> indexs = new ArrayList<Integer>();
		
		while(indexs.size()<5) {
			int random_int = (int)Math.floor(Math.random() * 13);
			if(!indexs.contains(random_int)){
				indexs.add(random_int);
			}
			
		}

		for(int i : indexs) {
			String BodyAddBaseCard = String.format("[%d,%d]",id,i);
			restTemplate.postForObject(uriAddBaseCard, BodyAddBaseCard, Hero.class);
		}
		

	}

	
	

}
