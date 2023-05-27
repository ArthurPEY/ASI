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

import com.sp.model.Room;
import com.sp.service.RoomService;


@RestController
public class RoomRestCrt {
	@Autowired
	RoomService rService;
	
	public List<String> Parse(String listStr){
		listStr = listStr.replace("[", "").replace("]", "");
		listStr = listStr.replace("\"", "");
		String[] split = listStr.split(",");
		List<String> list = Arrays.asList(split);
		
		return list;
	}
	
	
    @RequestMapping(method=RequestMethod.POST,value="/getroom")
    public Room getRoom(@RequestBody Integer roomId) {
        Room salle = rService.getRoom(roomId);
        return salle;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/delroom")
    public void delRoom(@RequestBody Integer roomId) {
        rService.delRoom(roomId);
    }
	
    @RequestMapping(method=RequestMethod.POST,value="/allroom")
    public List<Room> allRoom() {
        List<Room> allRoom = rService.allRoom();
        return allRoom;
    }
	
    @RequestMapping(method=RequestMethod.POST,value="/newroom")
    public int createRoom(@RequestBody String player1Id_mise) {
    	List<String> list = Parse(player1Id_mise);
    	int player1Id = Integer.parseInt(list.get(0));
    	int mise = Integer.parseInt(list.get(1));
    	
        int roomId = rService.createRoom(player1Id,mise);
        return roomId;
    }
	
    @RequestMapping(method=RequestMethod.POST,value="/joinroom")
    public void joinRoom(@RequestBody String rommId_player2Id) {
    	List<String> info = Parse(rommId_player2Id);
    	int roomId = Integer.parseInt(info.get(0));
    	int player2id = Integer.parseInt(info.get(1));
    	
        rService.joinRoom(roomId,player2id);
        
        
        
        
    }

	

	
}