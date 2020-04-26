package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	
	@Query(value="SELECT n FROM Note n  WHERE n.role=?1 and n.ownerId=?2") 
	public List<Note> getNotes(String role, Long ownerId);
 }
