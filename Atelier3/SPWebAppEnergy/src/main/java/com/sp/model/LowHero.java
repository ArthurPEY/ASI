package com.sp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lowheros")
public class LowHero {
	@Id
	private int id;
	
	public LowHero() {
	}

	public LowHero(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "LowHero [id=" + id + "]";
	}
	
	

}
