package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
 
	
	  
 }
