package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sp.controller.Hero;
import com.sp.repository.HeroRepository;

@Service
public class HeroService {
	@Autowired
	HeroRepository hRepository;
	
	List<Hero> allHero = new ArrayList<Hero>();
	
	@EventListener(ApplicationReadyEvent.class)
	public void initAllHeroList(){
	
		Hero IronMan = new Hero(0,null, "Hero","Iron Man","Iron Man","Lumière",
				"https://i.postimg.cc/fbgmsCfn/ironman.png",
					90,100,82,80);
		allHero.add(IronMan);
		
		Hero Hulk = new Hero(0,null,"Hero","Hulk","L'homme le plus fort du monde","Terre",
				"https://i.postimg.cc/zBMLsQj4/hulk.png" ,
					80,80,100,60);
		allHero.add(Hulk);
	    
		Hero CptAmerica = new Hero(0,null,"Hero","Captain America","Captain America","Terre",
				"https://i.postimg.cc/qvdbKJKD/cpamerica.png" ,
					92,90,75,100);
		allHero.add(CptAmerica);
		
		Hero Thanos = new Hero(0,null,"Vilain","Thanos","Le Titan fou","Ombre", 
				"https://i.postimg.cc/T3qkqzqd/thanos.png",
					100 ,100,100,100);
		allHero.add(Thanos);
		
		Hero SpiderMan = new Hero(0,null,"Hero","Spider-Man","L'Homme araignée","Air", 
				"https://i.postimg.cc/ncmSMjXQ/spiderman.png",
					72 ,100,92,70);
		allHero.add(SpiderMan);
		
		Hero ScarletWitch = new Hero(0,null, "Hero", "La Sorcière Écarlate","Ses pouvoirs de mutant couplés à sa magie la rende redoutable.","Feu",
		        "https://i.postimg.cc/zvgL8k2v/rouge.png",
		                70,66,80,61);
		allHero.add(ScarletWitch);
	
		Hero Vision = new Hero(0,null, "Hero", "Vision","Un androïde créé par Iron Man qui tire ses pouvoirs de sa pierre d'infinité.","Lumière",
		        "https://i.postimg.cc/0Qrcvbgj/vision.png",
		                90,80,85,73);
		allHero.add(Vision);
	
		Hero Venom = new Hero(0,null, "Vilain", "Venom","Créé à partir de l'ADN de Spider Man cet organisme prend le contrôle de son hôte pour tout détruire.","Ombre",
		        "https://i.postimg.cc/3w8tLtng/venom.png",
		                90,54,69,65);
		allHero.add(Venom);
	
		Hero TheThing = new Hero(0,null, "Hero", "La Chose","Il s'effrite quand il se frite avec quelqu'un.","Terre",
		        "https://i.postimg.cc/PfPRsFhy/lachose.png",
		                80,45,70,93);
		allHero.add(TheThing);
	
		Hero Thor = new Hero(0,null, "Hero", "Thor","Dieu de la foudre et fils d'Odin, rien de résiste à son marteau.","Air",
		        "https://i.postimg.cc/bvm341B4/thor.png",
		                80,75,85,86);
		allHero.add(Thor);
		
		Hero DrStrange= new Hero(0,null, "Hero", "Dr Strange","Ce n'est pas un surnom, il est vraiment médecin mais surtout le plus grand des sorciers.","Lumière",
		        "https://i.postimg.cc/sfq0V9gr/drstrange.png",
		                60,81,94,67);
		allHero.add(DrStrange);
	
		Hero AntMan = new Hero(0,null, "Hero", "Ant Man","Le héros aux tailles multiples.","Terre",
		        "https://i.postimg.cc/rmz0YTSS/antman.png",
		                80,64,78,72);
		allHero.add(AntMan);
	
		Hero DeadPool= new Hero(0,null, "Hero", "Deadpool","Il a brisé le 4ème mur et tout ceux sur son passage.","Feu",
		        "https://i.postimg.cc/grbxJ61D/deadpool.png",
		                150,35,65,51);
		allHero.add(DeadPool);
		
		
	}
	
	
	
	public void addHero(Hero h) {
		Hero createdHero = hRepository.save(h);
		System.out.println("Ajout du hero :");
		System.out.println(createdHero);
	}
	
	public Hero getHero(int id) {
		Optional<Hero> hOpt = hRepository.findById(id);
		if (hOpt.isPresent()) {
			System.out.println(String.format("Recuperation du hero id=%d",id));
			return hOpt.get();
		}else {
			return null;
		}
	}
	
	public List<Hero> getCardUser(int id){

		List<Hero> hOpt = hRepository.findByOwner(id);
		List<Hero> heroAvailable = new ArrayList<Hero>();
		for(int i = 0;i<hOpt.size();i++) {
			if(hOpt.get(i).isOnSell() == false) {
				heroAvailable.add(hOpt.get(i));
			}
		}
		System.out.println(String.format("Recuperation des heros de l'utilistateur id=%d",id));
		return heroAvailable;
	}
	
	public void delHero(int cardId) {
		hRepository.deleteById(cardId);
		System.out.println(String.format("Supression du hero id=%d de la BDD 'heros'",cardId));
	}

	public void updateEnergy(int cardId, int energy) {
		Optional<Hero> hOpt = hRepository.findById(cardId);
		Hero h = hOpt.get();
		int currentEnergy = h.getEnergy();
		h.setEnergy(currentEnergy+energy);
		System.out.println(String.format("MAJ de l'energy du hero %d, energy += %d ",cardId,energy));
		hRepository.save(h);
	}

	public int getEnergy(Integer cardId) {
		Optional<Hero> hOpt = hRepository.findById(cardId);
		if (hOpt.isPresent()) {
			System.out.println(String.format("Demande de l'energy du hero %d",cardId));
			return hOpt.get().getEnergy();
		}else {
			return -1;
		}
	}



	public void casewin(int userId, int index) {
		RestTemplate restTemplate = new RestTemplate();	

		
		String uriJoinerMoney = String.format("http://localhost:80/user/getmoney/%d",userId);
		int moneyCaseOpener = restTemplate.postForObject(uriJoinerMoney, int.class, int.class);
		
		if (moneyCaseOpener>=100) { // verification en plus de la verification JS
		
			String BodyChangeMoney = String.format("[%d,%d]",userId,-100);	
			String uriChangeMoney = "http://127.0.0.1:80/user/setmoney";
			restTemplate.postForObject(uriChangeMoney,BodyChangeMoney,String.class); // Prelevement du tirage case oppening
			
			Hero h = allHero.get(index);
			h.setOwner(userId);
			
			System.out.println(String.format("Gain de la carte %s par l'utilisateur id=%d",h.getName(),userId));
			hRepository.save(h);
			
			return;
		}
	}



	public List<Hero> basehero() {
		return this.allHero;
	}



	public void addbasehero(int userId,int index) {
		Hero h = allHero.get(index);
		h.setOwner(userId);
		hRepository.save(h);
	}
	
	
}
