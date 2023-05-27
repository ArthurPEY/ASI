package com.sp.model;



import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "combats")
public class Combat {
	@Id
	private Integer id;
	private Integer player1;
	private Integer player2;
	private Integer HeroIdP1;
	private Integer HeroIdP2;
	private Integer mise;
	//private Integer firstToPlay;
	private Integer winner;
	private Integer looser;
	private boolean done;

	
	public Combat() {
	}
	
	public Combat(Integer combatId, Integer player1, Integer player2, Integer mise) {
		this.id = combatId;
		this.player1 = player1;
		this.player2 = player2;
		this.mise = mise;
		this.HeroIdP1 = null;
		this.HeroIdP2 = null;
		this.winner = null;
		this.looser = null;
		//this.firstToPlay = null;
		this.done = false;
	}

	
/*
	public Integer getFirstToPlay() {
		return firstToPlay;
	}

	public void setFirstToPlay(Integer firstToPlay) {
		this.firstToPlay = firstToPlay;
	}
*/
	
	
	public boolean isDone() {
		return done;
	}

	public Integer getWinner() {
		return winner;
	}

	public void setWinner(Integer winner) {
		this.winner = winner;
	}

	public Integer getLooser() {
		return looser;
	}

	public void setLooser(Integer looser) {
		this.looser = looser;
	}

	

	public void setDone(boolean done) {
		this.done = done;
	}

	public Integer getMise() {
		return mise;
	}

	public void setMise(Integer mise) {
		this.mise = mise;
	}

	public Integer getHeroIdP1() {
		return HeroIdP1;
	}

	public void setHeroIdP1(Integer heroIdP1) {
		HeroIdP1 = heroIdP1;
	}

	public Integer getHeroIdP2() {
		return HeroIdP2;
	}

	public void setHeroIdP2(Integer heroIdP2) {
		HeroIdP2 = heroIdP2;
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