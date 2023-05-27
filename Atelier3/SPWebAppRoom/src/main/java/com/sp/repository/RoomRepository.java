package com.sp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.sp.model.Room;


public interface RoomRepository extends JpaRepository<Room, Integer> {

	public List<Room> findByPlayer1(Integer id);
	public List<Room> findByPlayer2(Integer id);
}
