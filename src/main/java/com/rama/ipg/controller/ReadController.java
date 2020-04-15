package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ReadController {
	
	private Logger logger = LoggerFactory.getLogger(ReadController.class);
	
	@GetMapping("/read") 
	public String getAllHostelSubscriptionDetails() {
		 
		logger.info("In::");
		return "Welcome Message.";
	}

}
