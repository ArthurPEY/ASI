package com.sp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.sp.model.LowHero;

public interface EnergyRepository extends JpaRepository<LowHero, Integer> {

}
