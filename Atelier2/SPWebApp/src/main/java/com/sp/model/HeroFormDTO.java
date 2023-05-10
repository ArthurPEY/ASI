package com.sp.model;

public class HeroFormDTO {
	private Integer id;
	private Integer owner;
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

	public HeroFormDTO() {
		this.id = 1;
		this.name = "";
		this.family = "";
		this.description = "";
		this.affinity = "";
		this.imgUrl = "";
		this.hp = 1;
		this.energy = 1;
		this.attack = 1;
		this.defence = 1;

	}

	public HeroFormDTO(int id, Integer owner, String family, String name, String description, String affinity, String imgUrl, int hp,
			int energy, int attack, int defence) {
		this.id = id;
		this.name = name;
		this.family = family;
		this.description = description;
		this.affinity = affinity;
		this.imgUrl = imgUrl;
		this.hp = hp;
		this.energy = energy;
		this.attack = attack;
		this.defence = defence;
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
