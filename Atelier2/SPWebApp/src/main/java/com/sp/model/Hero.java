package com.sp.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hero {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer owner;
	private Integer price;
	private boolean onSell;
	private String family;
	private String name;
	private String description;
	private String affinity;
	private String imgUrl;
	private int hp;
	private int energy;
	private int attack;
	private int defence;
	private int stock;
	
	public Hero() {
	}

	public Hero(int id,Integer owner,String family,String name,String description,String affinity,String imgUrl,int hp,int energy,int attack,int defence) {
		super();

		this.id=id;
		this.owner=owner;
		this.onSell=false;
		this.price=100; /*Prix par defaut*/
		this.name=name;
		this.family=family;
		this.description=description;
		this.affinity=affinity;
		this.imgUrl=imgUrl;
		this.hp=hp;
		this.energy=energy;
		this.attack=attack;
		this.defence=defence;
	}
	
	public Hero(int id,Integer owner,String family,String name,String description,String affinity,String imgUrl,
			int hp,int energy,int attack,int defence,boolean onSell,int stock,int price) {
		
		super();
		this.id=id;
		this.owner=owner;
		this.onSell=onSell;
		this.stock = stock;
		this.price=price; /*Prix par defaut*/
		this.name=name;
		this.family=family;
		this.description=description;
		this.affinity=affinity;
		this.imgUrl=imgUrl;
		this.hp=hp;
		this.energy=energy;
		this.attack=attack;
		this.defence=defence;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String affinity) {
		this.affinity = affinity;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", family=" + family + ", name=" + name + ", description=" + description
				+ ", affinity=" + affinity + ", imgUrl=" + imgUrl + ", hp=" + hp + ", energy=" + energy + ", attack="
				+ attack + ", defence=" + defence + "]";
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public boolean isOnSell() {
		return onSell;
	}

	public void setOnSell(boolean onSell) {
		this.onSell = onSell;
	}



	
}

