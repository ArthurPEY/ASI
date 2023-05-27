  package com.sp.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sp.model.LowHero;
import com.sp.repository.EnergyRepository;

  @Service
  public class EnergyService {
	  @Autowired
	  EnergyRepository eRepository;
	  
	  public boolean timerOn = false;
	  
	  
      public void addLowEnergy(int id) {
    	  Optional<LowHero> lhOpt = eRepository.findById(id);
    	  if (lhOpt.isPresent()) {
    		  System.out.println(String.format("Hero id=%d deja present",id));
              }
    	  else {
    		  LowHero h= new LowHero(id) ;
    		  eRepository.save(h);
    		  System.out.println("Hero fatigué id=%d ajoute !");
    	  }

      }
      
      public void updateHeroEnergy(int heroid) {
    	  RestTemplate restTemplate = new RestTemplate();
    	  
          String uriEnergyUp = "http://127.0.0.1:80/hero/updateenergy";
          String BodyIdNrj = String.format("[%d,%d]",heroid,+1);
          restTemplate.postForObject(uriEnergyUp,BodyIdNrj,String.class); // 
    	  
      }
      
      public int getHeroEnergy(int heroid) {
    	  RestTemplate restTemplate = new RestTemplate();
    	  
          String uriEnergyGet = String.format("http://127.0.0.1:80/hero/getenergy/%d",heroid);
          String response = restTemplate.postForObject(uriEnergyGet, String.class, String.class);
          return Integer.parseInt(response);
      }
      
      
      @EventListener(ApplicationReadyEvent.class)
      public void timerEnergy() {
    	  
    	  while(true) {
    		  try
    		  {
    			  Thread.sleep(2000);
    	    	  for (LowHero i : eRepository.findAll()) {
    	    		  System.out.println(String.format("Ajout de 1 energy au hero %d",i.getId()));
    	    		  updateHeroEnergy(i.getId());
    	    		  if (getHeroEnergy(i.getId()) > 99) {
    	    			  eRepository.delete(i);
    	    		  }
    	    	  }
    	      }
    	      catch(InterruptedException ex)
    	      {
    	          ex.printStackTrace();
    	      }
    	  }

      }
      
 

  }
