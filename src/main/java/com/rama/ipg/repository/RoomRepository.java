package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  
	@Query(value="SELECT r FROM Room r WHERE r.hostelId=?1") 
	public List<Room> getRooms(Long hostelId);
	 
	@Modifying
	@Query("delete from Room r where r.hostelId=?1")
	public void deleteByHostelId(Long hostelId);
	
	
 }
