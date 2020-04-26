package com.rama.ipg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Supervisor;
import com.rama.ipg.repository.HostelRepository;
import com.rama.ipg.repository.SupervisorRepository;
import com.rama.ipg.service.SupervisorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class SupervisorController {
	
	private Logger logger = LoggerFactory.getLogger(SupervisorController.class);
	 
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@Autowired
	private  HostelRepository hostelRepository;
	
	
	@PostMapping("/supervisors") 
	public Supervisor saveSupervisor(@RequestBody Supervisor supervisor) {
		
		logger.info("In::/supervisor:"+supervisor);
		
		supervisorService.sendJoiningAlerts(supervisor);
		supervisor = supervisorRepository.save(supervisor);
		
		logger.info("Out::"+supervisor);
		
		return supervisor;
	}
	
	
	@GetMapping("/supervisors/{ownerId}") 
	public List<Supervisor> getSupervisors(@PathVariable(value = "ownerId") Long ownerId ) {
		
		logger.info("In::/supervisors/"+ownerId); 
		List<Supervisor> supervisors = supervisorRepository.getSupervisorsForOwner(ownerId); 
		
		for(Supervisor supervisor : supervisors){
			supervisor.setManagedHostels( hostelRepository.getHostelsForSupervisor(supervisor.getPhoneNumber()));
		}
		
		logger.info("Out::"+supervisors);
		
		return supervisors;
	}
	
	@GetMapping("/supervisors/") 
	public Supervisor getSupervisor(@RequestParam("id") Long id,@RequestParam("pwd") String pwd ) {
		
		logger.info("In::/supervisors/id:"+id); 
		Supervisor supervisor = supervisorRepository.getSupervisorWithPwd(id, pwd); 
		logger.info("Out::"+supervisor);
		
		return supervisor;
	}
	
	@DeleteMapping("/supervisors") 
	public Supervisor deleteSupervisor(@RequestParam("id") Long id ) {
		
		logger.info("In::/supervisors/id:"+id); 
		Supervisor supervisor = supervisorRepository.deleteByPhoneNumber(id);
		logger.info("Out::"+supervisor);
		
		return supervisor;
	}
	  
}
