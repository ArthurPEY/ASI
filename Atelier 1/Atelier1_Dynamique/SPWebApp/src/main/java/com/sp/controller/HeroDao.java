package com.sp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sp.model.Hero;

@Service
public class HeroDao {

    private List<Hero> myHeroList;

    public HeroDao(){
        myHeroList =new ArrayList<Hero>();
        createHeroList();
    }

    private void createHeroList(){
        Hero h1=new Hero("Jose","https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
        		"14h","3 comments","17 likes","Buy","testDesc","testFamily","testAffinity");
        Hero h2=new Hero("Jose","https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
        		"14h","3 comments","17 likes","Buy","testDesc","testFamily","testAffinity");

        myHeroList.add(h1);
        myHeroList.add(h2);
    }

	public List<Hero> getMyHeroList() {
		return myHeroList;
	}
	
	public Hero addHero(String name, String image_src, String description, String family, String affinity) {
		Hero h=new Hero(name,  image_src,  description,  family,  affinity);
		this.myHeroList.add(h);
        return h;


	}
    

}
