package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

	 
	//@Query(value="UPDATE hostel h set h.supervisor_id=?3  WHERE h.name=?1 and h.owner_id=?2",  nativeQuery=true) 
	//public void updateSupervisor(String hostelName, String ownerId, String supervisorId);
	
	
	@Query(value="SELECT h FROM Hostel h WHERE h.name=?1 and h.ownerId=?2") 
	public Hostel  getHostelByNameAndId(String hostelName, String ownerId);
	
	
	
	@Query(value="SELECT h.name FROM Hostel h WHERE h.supervisorId=?1") 
	public List<String> getHostelsForSupervisor(String supervisorId);
	
	
	@Query(value="SELECT h FROM Hostel h WHERE h.ownerId=?1") 
	public List<Hostel> getHostelsByOwner( String ownerId);
	
	
	@Query(value="SELECT h FROM Hostel h WHERE h.ownerId=?1 and h.supervisorId=?2") 
	public List<Hostel>getHostelsBySupervisor( String ownerId,  String supervisorId);
	
	
	
	  
 }
