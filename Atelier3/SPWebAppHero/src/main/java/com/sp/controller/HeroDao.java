package com.sp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.sp.SpAppHero;
import com.sp.controller.Hero;
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
    
	public List<Hero> getMyHeroList() {
		return myHeroList;
	}
	

	
	

}
