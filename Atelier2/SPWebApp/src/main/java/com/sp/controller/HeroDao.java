package com.sp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.sp.SpAppHero;
import com.sp.model.Hero;
import com.sp.service.HeroService;

@Service
public class HeroDao {
	 @Autowired
     HeroService hService;

    private List<Hero> myHeroList;

    public HeroDao(){
        myHeroList =new ArrayList<Hero>();
        ;
    }
    private void createHeroList(){
        Hero h1=new Hero(1,1,"Jose","Jose","ok","feu","https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
        		50,50,50,50);
    	 Hero h2=new Hero(1,1,"Jose","Jose","ok","feu","https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
         		50,50,50,50);

        myHeroList.add(h1);
        myHeroList.add(h2);
        hService.addHero(h1);
        
    }
    

	public List<Hero> getMyHeroList() {
		return myHeroList;
	}
	
	public Hero addHero(Integer owner,String family,String name,String description,String affinity,String imgUrl,int hp,int energy,int attack,int defence) {
		Hero h=new Hero(1,owner,family,name,description,affinity,imgUrl,hp, energy, attack, defence);
		
		this.myHeroList.add(h);
        return h;


	}
	
	/*Hero(int id,Integer owner,String family,String name,String description,String affinity,String imgUrl,int hp,int energy,int attack,int defence)*/
	
	public void createBaseCards() {	
		Hero h1 = new Hero(-999,-999, "Hero","Iron Man","Iron Man","Lumière","https://e7.pngegg.com/pngimages/355/993/png-clipart-iron-man-captain-america-chibi-iron-man-marvel-avengers-assemble-superhero.png",
				90,100,82,80,true,999,500);
	    
		hService.addHero(h1);
	}
	

}
