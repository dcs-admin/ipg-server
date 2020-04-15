package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	  
 }
