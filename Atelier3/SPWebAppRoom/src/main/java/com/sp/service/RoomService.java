package com.sp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sp.model.Room;
import com.sp.repository.RoomRepository;


@Service
public class RoomService {

	@Autowired
	RoomRepository rRepository;
	
	

	public int createRoom(Integer player1Id,Integer mise) {
		RestTemplate restTemplate = new RestTemplate();
		
		
		String uriCreatorMoney = String.format("http://localhost:80/user/getmoney/%d",player1Id);
		int CreatorMoney = restTemplate.postForObject(uriCreatorMoney, int.class, int.class);
		
		if (CreatorMoney>=mise) {
			Room r = new Room(player1Id,mise);
			
			rRepository.save(r);
			String uriCreatorSetMoney = "http://localhost:80/user/setmoney";
			String BodyCreatorSetMoney = String.format("[%d,%d]",player1Id,-mise);
			restTemplate.postForObject(uriCreatorSetMoney,BodyCreatorSetMoney,String.class); // Payement de la mise
			System.out.println(String.format("Salle cree par %d avec une msie de %d",player1Id,mise));
			return r.getId();
		}
		else {
			System.out.println("Pas assez d'argent");
		}
		return -1;
		
	}
	
	public Room getRoom(Integer roomId) {
		Optional<Room> rOpt = rRepository.findById(roomId);
		Room r = rOpt.get();
		System.out.println(String.format("Demmande de la salle %d",roomId));
		System.out.println(r);
		return r;
	}



	public List<Room> allRoom() {
		   List<Room> salles = new ArrayList<Room>();
		   rRepository.findAll().forEach(Room -> salles.add(Room));
		   System.out.println("Demande de toutes les salles");
		   System.out.println(salles);
		   return salles;
	}

	public void joinRoom(Integer roomId,Integer player2Id) {
		RestTemplate restTemplate = new RestTemplate();
		
		Optional<Room> rOpt = rRepository.findById(roomId);
		Room r = rOpt.get();
		
		
		String uriJoinerMoney = String.format("http://localhost:80/user/getmoney/%d",player2Id);
		int JoinerMoney = restTemplate.postForObject(uriJoinerMoney, int.class, int.class);
		
		int mise = r.getMise();
		
		if (JoinerMoney > mise) {
			r.setPlayer2(player2Id);
			rRepository.save(r);
			
			String uriCreatorSetMoney = "http://localhost:80/user/setmoney";
			String BodyCreatorSetMoney = String.format("[%d,%d]",player2Id,-mise);
			restTemplate.postForObject(uriCreatorSetMoney,BodyCreatorSetMoney,String.class); // Payement de la mise
			
			String uriCreateCombat = "http://localhost:80/combat/createcombat";
			restTemplate.postForObject(uriCreateCombat, r, String.class); // Lancement du combat
			System.out.println(String.format("Salle n %d rejoin par %d",roomId,player2Id));
			//rRepository.delete(r);
		}
		else {
			System.out.println("Pas assez d'argent");
		}

	}

	public void delRoom(Integer roomId) {
		Optional<Room> rOpt = rRepository.findById(roomId);
		Room r = rOpt.get();
		System.out.println(String.format("Suppression de la salle %d",roomId));
		rRepository.delete(r);
	}
	

	

}
