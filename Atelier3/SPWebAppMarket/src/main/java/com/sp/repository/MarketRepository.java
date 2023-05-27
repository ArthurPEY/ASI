package com.sp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sp.model.Hero;

public interface MarketRepository extends JpaRepository<Hero, Integer> {

	public List<Hero> findByName(String name);
	public List<Hero> findByOwner(int owner);
	public List<Hero> findByOnSell(boolean onSell);
}
