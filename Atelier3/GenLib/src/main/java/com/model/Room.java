package com.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer player1;
	private Integer player2;
	private Integer mise;
	
	public Room() {
	}
	
	public Room(Integer player1,Integer mise) {
		this.player1 = player1;
		this.mise = mise;
		this.player2 = null;
	}

	public Integer getMise() {
		return mise;
	}

	public void setMise(Integer mise) {
		this.mise = mise;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlayer1() {
		return player1;
	}

	public void setPlayer1(Integer player1) {
		this.player1 = player1;
	}

	public Integer getPlayer2() {
		return player2;
	}

	public void setPlayer2(Integer player2) {
		this.player2 = player2;
	}
	
	
	
}