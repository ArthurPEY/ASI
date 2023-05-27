package com.sp.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Room;
import com.sp.model.Combat;
import com.sp.service.CombatService;


@RestController
public class CombatRestCrt {
	@Autowired
	CombatService cService;
	
	public List<String> Parse(String listStr){
		listStr = listStr.replace("[", "").replace("]", "");
		listStr = listStr.replace("\"", "");
		String[] split = listStr.split(",");
		List<String> list = Arrays.asList(split);
		
		return list;
	}
	
    
    @RequestMapping(method=RequestMethod.POST,value="/createcombat")
    public void createcombat(@RequestBody Room salle) {
        cService.createcombat(salle);
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/getcombat")
    public Combat getcombat(@RequestBody int combatId) {
        Combat c = cService.getcombat(combatId);
        return c;
    }
 
    @RequestMapping(method=RequestMethod.POST,value="/allcombat")
    public List<Combat> allcombat() {
        List<Combat> Combats = cService.allcombat();
        return Combats;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/setcard")
    public void setcard(@RequestBody String combatId_userId_cardId) {
    	List<String> list = Parse(combatId_userId_cardId);
    	int combatId = Integer.parseInt(list.get(0));
    	int userId = Integer.parseInt(list.get(1));
    	int cardId = Integer.parseInt(list.get(2));
    
        cService.setcard(combatId,userId,cardId);
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/startcombat")
    public List<List<Integer>> startcombat(@RequestBody int combatId) {
 
    	List<List<Integer>> HealtMonitor = cService.startcombat(combatId);
    	return HealtMonitor;
    }
	

	
}