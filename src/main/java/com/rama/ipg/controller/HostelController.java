package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Hostel;
import com.rama.ipg.model.Room;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.HostelRepository;
import com.rama.ipg.repository.RoomRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class HostelController {
	
	private Logger logger = LoggerFactory.getLogger(HostelController.class);
	
	
	@Autowired
	private HostelRepository hostelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BedRepository bedRepository;
	
	
	
	@PostMapping("/hostels") 
	public Hostel saveHostel(@RequestBody Hostel  hostel) {
		
		logger.info("In::");
		Hostel savedHostel = hostelRepository.save(hostel);
		
		hostel.getRooms().forEach(room -> {
			 room.setHostelId(savedHostel.getId());
			 Room savedRoom = roomRepository.save(room); 
			room.getBeds().forEach(bed -> {
				bed.setHostelId(savedHostel.getId());
				bed.setRoomId(savedRoom.getId());
				bedRepository.save(bed);
			}); 
		});
		 
		
		logger.info("Out::"+hostel);
		return hostel;
	}
	
	
	@PostMapping("/hostels/supervisor") 
	public String updateSupervisors(@RequestParam("hostelName") String hostelName,
			@RequestParam("ownerId") String ownerId ,
			@RequestParam("supervisorId") String supervisorId) {
		
		logger.info("In::/hostels:"+ownerId+";"+hostelName); 
		  
		Hostel hostel = hostelRepository.getHostelByNameAndId(hostelName, ownerId);
		if(hostel != null){
			hostel.setSupervisorId(supervisorId);
			hostelRepository.save(hostel); 
		}
		
		
		logger.info("Out::");
		return "DONE";
	}
	

}
