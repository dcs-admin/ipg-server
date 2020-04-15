package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Note;
import com.rama.ipg.repository.NoteRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class NoteController {
	
	private Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	
	@Autowired
	private NoteRepository noteRepository;
	
	@PostMapping("/notes") 
	public Note saveTenant(@RequestBody Note  note) {
		logger.info("In::");
		note = noteRepository.save(note);
		logger.info("obj::"+note); 
		
		return note;
	}
	
	

}
