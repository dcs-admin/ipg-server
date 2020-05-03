package com.rama.ipg.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Notification;
import com.rama.ipg.repository.NotificationRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class NotificationController {
	
	private Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	//@Autowired
	//private NotificationService notificationService;
	
	@Autowired
	private NotificationRepository notificationRepository;
	 
	@PostMapping("/notifications") 
	public Notification saveNotification(@RequestBody Notification  notification) {
		logger.info("In::");
		notification = notificationRepository.save(notification);
		logger.info("obj::"+notification); 
		
		return notification;
	} 
	
	@PutMapping("/notifications/{id}")
	public Notification updateNotification(@PathVariable Long id, @Valid @RequestBody Notification notification) {
		logger.info("In::"+id); 
		
		if(notificationRepository.findById(id).isPresent()){
			notification.setId(id);
			notification = notificationRepository.save(notification);
		} 
		
		logger.info("Out::"+notification);
		return notification;
	}
	
	
	@DeleteMapping("/notifications/{id}")
	public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
		logger.info("IN::deleteNotification::" + id);

		notificationRepository.findById(id).ifPresent(notification ->{ 
			
			notificationRepository.deleteById(id); 
			
		}); 
		
		return ResponseEntity.ok().build();
	}
	
	
	
	@GetMapping("/notifications") 
	public List<Notification> getNotifications(
			//@PathVariable("role") String role,
			//@RequestParam("ownerId") Long ownerId
			) {
		
		logger.info("In::/notifications"); 
		
		List<Notification> notifications = notificationRepository.findAll();
		
		logger.info("Out::"+notifications);
		return notifications;
	}
	
	
	 
	
	

}
