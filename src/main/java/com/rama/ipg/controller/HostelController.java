package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Hostel;
import com.rama.ipg.repository.HostelRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class HostelController {
	
	private Logger logger = LoggerFactory.getLogger(HostelController.class);
	
	
	@Autowired
	private HostelRepository hostelRepository;
	
	
	
	@PostMapping("/hostels") 
	public Hostel saveHostel(@RequestBody Hostel  hostel) {
		
		logger.info("In::");
		hostel = hostelRepository.save(hostel); 
		
		logger.info("Out::"+hostel);
		return hostel;
	}
	
	

}
