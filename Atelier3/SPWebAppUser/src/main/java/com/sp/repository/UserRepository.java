package com.sp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.sp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findByName(String name);
}
