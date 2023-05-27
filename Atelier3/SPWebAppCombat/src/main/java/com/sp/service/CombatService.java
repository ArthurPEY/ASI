package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.model.Hero;
import com.model.Room;
import com.sp.repository.CombatRepository;
import com.sp.model.Combat;


@Service
public class CombatService {

	@Autowired
	CombatRepository cRepository;

	public void createcombat(Room salle) {
		int combatId = salle.getId();
		int player1Id = salle.getPlayer1();
		int player2Id = salle.getPlayer2();
		int mise = salle.getMise();
		Combat c = new Combat(combatId,player1Id,player2Id,mise);
		System.out.println(String.format("Creation du combat %d, P1:%d,P2:%d,mise:%d",combatId,player1Id,player2Id,mise));
		cRepository.save(c);
	}

	public List<Combat> allcombat() {
		   List<Combat> Combats = new ArrayList<Combat>();
		   cRepository.findAll().forEach(Combat -> Combats.add(Combat));
		   System.out.println("Recuperation de tout les combats :");
		   System.out.println(Combats);
		   return Combats;
	}

	public void setcard(int combatId, int userId, int cardId) {
		Optional<Combat> cOpt = cRepository.findById(combatId);
		Combat c = cOpt.get();
		if (c.getPlayer1() == userId) {
			c.setHeroIdP1(cardId);
		}
		else {
			c.setHeroIdP2(cardId);
		}
		System.out.println(String.format("Ajout de la carte %d du joueur %d sur la room %d",cardId,userId,combatId));
		cRepository.save(c);
		
	}

	public List<List<Integer>> startcombat(int combatId) {
		System.out.println(String.format("Lancement du combat %d",combatId));
		Optional<Combat> cOpt = cRepository.findById(combatId);
		Combat c = cOpt.get();
		
		int looser,winner,winnerHeroId,looserHeroId = -1;

		List<List<Integer>> healthMonitor = new ArrayList<List<Integer>>(); 
		
		if (c.getHeroIdP1() != null && c.getHeroIdP2() != null) {
			RestTemplate restTemplate = new RestTemplate();
			
			String uriCardP1 = String.format("http://127.0.0.1:80/hero/gethero/%d",c.getHeroIdP1());
			Hero hP1 = restTemplate.postForObject(uriCardP1, Integer.class, Hero.class); // Hero joueur 1
			
			String uriCardP2 = String.format("http://127.0.0.1:80/hero/gethero/%d",c.getHeroIdP2());
			Hero hP2 = restTemplate.postForObject(uriCardP2, Integer.class, Hero.class); // Hero joueur 2
			
			
			
			List<Integer> healthMonitorP1 = new ArrayList<Integer>();
			healthMonitorP1.add(hP1.getHp());
			List<Integer> healthMonitorP2 = new ArrayList<Integer>();
			healthMonitorP2.add(hP2.getHp());
			
			int attackP1 = hP1.getAttack();
			int attackP2 = hP2.getAttack();
			
			int defenceP1 = hP1.getDefence();
			int defenceP2 = hP2.getDefence();
			
			int vitesseP1 = hP1.getVitesse();
			int vitesseP2 = hP2.getVitesse();
			
			/*
			if (c.getFirstToPlay() == null) {
				if      (vitesseP1 > vitesseP2) {c.setFirstToPlay(hP1.getOwner());}
				else if (vitesseP1 < vitesseP2) {c.setFirstToPlay(hP2.getOwner());}
				else 	{c.setFirstToPlay(hP2.getOwner());} // Choix arbitraire, le créateur a la priorité
			}*/
			
			while(hP1.getHp() >= 0 && hP2.getHp() >= 0) {
				if(vitesseP1 > vitesseP2) {
					int currentHp2 = hP2.getHp();
					int newHp2 = (int)(currentHp2 - attackP1/4);
					hP2.setHp(newHp2);
					healthMonitorP2.add(newHp2);
					if (hP2.getHp() <= 0){break;}
					
					int currentHp1 = hP1.getHp();
					int newHp1 = (int)(currentHp1 - attackP2/4);
					hP1.setHp(newHp1);
					healthMonitorP1.add(newHp1);
				}
				else {
					int currentHp1 = hP1.getHp();
					int newHp1 = (int)(currentHp1 - attackP2/4);
					hP1.setHp(newHp1);
					healthMonitorP1.add(newHp1);
					if (hP1.getHp() <= 0){break;}
					
					int currentHp2 = hP2.getHp();
					int newHp2 = (int)(currentHp2 - attackP1/4);
					hP2.setHp(newHp2);
					healthMonitorP2.add(newHp2);
				}
			}
			
			
			healthMonitor.add(healthMonitorP1);
			healthMonitor.add(healthMonitorP2);
			
			
			
			
			if (hP1.getHp() > 0) {
				c.setWinner(c.getPlayer1());
				winner = c.getPlayer1();
				winnerHeroId = c.getHeroIdP1();
				
				looser = c.getPlayer2();
				c.setLooser(c.getPlayer2());
				looserHeroId = c.getHeroIdP2();
			}
			else {
				winner = c.getPlayer2(); 
				c.setWinner(c.getPlayer2());
				winnerHeroId = c.getHeroIdP2();
			
				looser = c.getPlayer1();
				c.setLooser(c.getPlayer1());
				looserHeroId = c.getHeroIdP1();
			}
			
			cOpt = cRepository.findById(combatId);
			c = cOpt.get();
			
			if (c.isDone()) {
				//cRepository.delete(c);
				return healthMonitor;
			}
			
			c.setDone(true);
			cRepository.save(c);
			
			System.out.println(String.format("Combat %d. Gagnant : %d, Perdant; %d",combatId,winner,looser));
			
			
			
			String BodyWinner = String.format("[%d,%d]",winner,+2*c.getMise());	
			String uriWinnerSet = "http://127.0.0.1:80/user/setmoney";
			restTemplate.postForObject(uriWinnerSet,BodyWinner,String.class); // Gain de la mise
			
			
			String uriWinnerSetEnergy = "http://127.0.0.1:80/hero/updateenergy";
			String BodyWinnerSetEnergy = String.format("[%d,%d]",winnerHeroId,-5);
			restTemplate.postForObject(uriWinnerSetEnergy,BodyWinnerSetEnergy,String.class); // Perte de 5 energy pour le gagnant
			
			String uriLooserSetEnergy = "http://127.0.0.1:80/hero/updateenergy";
			String BodyLooserSetEnergy = String.format("[%d,%d]",looserHeroId,-15);
			restTemplate.postForObject(uriLooserSetEnergy,BodyLooserSetEnergy,String.class); // Perte de 15 energy pour le perdant
			
			
			String uriWinnerAddLowEnergy = String.format("http://127.0.0.1:80/energy/addlowenergy/%d",winnerHeroId);
			String uriLooserAddLowEnergy = String.format("http://127.0.0.1:80/energy/addlowenergy/%d",looserHeroId);
			restTemplate.postForObject(uriWinnerAddLowEnergy,String.class,String.class);
			restTemplate.postForObject(uriLooserAddLowEnergy,String.class,String.class);
			System.out.println(String.format("Update des valeurs du Gagant %d et perdant; %d",winner,looser));
			
			
		}

		
		return healthMonitor;
		
	}

	public Combat getcombat(int combatId) {
		Optional<Combat> rOpt = cRepository.findById(combatId);
		Combat c = rOpt.get();
		System.out.println(String.format("Demande du combat n %d",combatId));
		return c;
		
	}


	

	

}
