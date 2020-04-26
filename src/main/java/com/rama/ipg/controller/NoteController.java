package com.rama.ipg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Note;
import com.rama.ipg.repository.NoteRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class NoteController {
	
	private Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	//@Autowired
	//private NoteService noteService;
	
	@Autowired
	private NoteRepository noteRepository;
	 
	@PostMapping("/notes") 
	public Note saveNote(@RequestBody Note  note) {
		logger.info("In::");
		note = noteRepository.save(note);
		logger.info("obj::"+note); 
		
		return note;
	} 
	
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable Long id) {
		logger.info("IN::deleteTenant::" + id);

		noteRepository.findById(id).ifPresent(note ->{ 
			
			noteRepository.deleteById(id); 
			
		}); 
		
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/notes/{role}/all") 
	public List<Note> getNotes(
			@PathVariable("role") String role,
			@RequestParam("ownerId") Long ownerId
			) {
		
		logger.info("In::/notes/{role}"+role+";o"+ownerId); 
		
		List<Note> notes = noteRepository.getNotes(role, ownerId ); 
		
		logger.info("Out::"+notes);
		return notes;
	}
	
	
	 
	
	

}
