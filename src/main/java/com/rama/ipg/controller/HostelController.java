package com.rama.ipg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.web.multipart.MultipartFile;

import com.rama.ipg.constants.IPGConstants;
import com.rama.ipg.model.ContactInfo;
import com.rama.ipg.model.Hostel;
import com.rama.ipg.model.Room;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.ContactInfoRepository;
import com.rama.ipg.repository.FacilitiesRepository;
import com.rama.ipg.repository.HostelRepository;
import com.rama.ipg.repository.RoomRepository;
import com.rama.ipg.service.StorageService;

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
	
	@Autowired
	private ContactInfoRepository contactInfoRepository;
	
	@Autowired
	private FacilitiesRepository facilitiesRepository;
	
	@Autowired
	private StorageService storageService;
	
	
	@GetMapping("/hostels") 
	public List<Hostel> findAllHostels() {
		
		List<Hostel> hostels = new ArrayList<>();
		
		for(Hostel hostel : hostelRepository.findAll()){
			hostels.add(this.getHostel(hostel.getId()));
		}
		
		return hostels;
		
	}
	
	@PostMapping("/hostels") 
	public Hostel saveHostel(@RequestBody Hostel  hostel) {
		
		logger.info("In::");
		Hostel savedHostel = hostelRepository.save(hostel);
		hostel.setId(savedHostel.getId());
		
		hostel.getRooms().forEach(room -> {
			 room.setHostelId(savedHostel.getId());
			 Room savedRoom = roomRepository.save(room); 
			 room.setRoomId(savedRoom.getRoomId());
			 room.getBeds().forEach(bed -> {
				bed.setHostelId(savedHostel.getId());
				bed.setRoomId(savedRoom.getRoomId());
				bed = bedRepository.save(bed);
			}); 
		});
		
		hostel.getFacilities().setHostelId(savedHostel.getId());
		facilitiesRepository.save(hostel.getFacilities());
		
		for(ContactInfo contactInfo : hostel.getContacts()){
			contactInfo.setHostelId(savedHostel.getId());
			contactInfo.setHostelName(savedHostel.getName());
			contactInfoRepository.save(contactInfo);
		}
		
		logger.info("Out::"+hostel);
		return hostel;
	}
	
	
	@PostMapping("/hostels/supervisor") 
	public String updateSupervisors(@RequestParam("hostelName") String hostelName,
			@RequestParam("ownerId") Long ownerId ,
			@RequestParam("supervisorId") Long supervisorId) {
		
		logger.info("In::/hostels:"+ownerId+";"+hostelName); 
		  
		Hostel hostel = hostelRepository.getHostelByNameAndId(hostelName, ownerId);
		if(hostel != null){
			hostel.setSupervisorId(supervisorId);
			hostelRepository.save(hostel); 
		}
		
		
		logger.info("Out::");
		return "DONE";
	}
	 
	
	@DeleteMapping("/hostels/{hostelId}")
	@Transactional
	public ResponseEntity<?> deleteHostel(@PathVariable("hostelId") Long hostelId) {
		logger.info("IN::deleteHostel::" + hostelId);

		hostelRepository.deleteById(hostelId); 
		
		roomRepository.deleteByHostelId(hostelId);
		
		bedRepository.deleteByHostelId(hostelId);
		
		contactInfoRepository.deleteByHostelId(hostelId);
		
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/hostels/{hostelId}") 
	public Hostel getHostel(
			@PathVariable("hostelId") Long hostelId 
			) {
		
		logger.info("In::/hostels/{hostelId}"+hostelId); 
		Hostel hostel = null;
		Optional<Hostel> hostelOp = hostelRepository.findById(hostelId);
		if(hostelOp.isPresent()){
			hostel = hostelOp.get();
			 
			hostel.setRooms(roomRepository.getRooms(hostel.getId())); 
			hostel.getRooms().forEach(room -> { 
				 room.setBeds(bedRepository.getBeds(room.getHostelId(), room.getRoomId()));
				}
			 );
			
			hostel.setFacilities(facilitiesRepository.findByHostelId(hostel.getId()));
			
			hostel.setContacts(contactInfoRepository.findByHostelId(hostel.getId()));
		} 
		 
		logger.info("Out::"+hostel);
		return hostel;	 
		
		
	}
	
	
	@GetMapping("/hostels-contacts/{hostelId}") 
	public List<ContactInfo> getContactsFromHostel(@PathVariable("hostelId") Long hostelId){
		logger.info("In::/hostels-contacts/{hostelId}"+hostelId); 
		logger.info("Out");
		return contactInfoRepository.findByHostelId(hostelId);
		
	}
	

	@GetMapping("/hostels/byrole/{role}") 
	public List<Hostel> getHostels(
			@PathVariable("role") String role,
			@RequestParam("ownerId") Long ownerId ,
			@RequestParam("supervisorId") Long supervisorId
			) {
		
		logger.info("In::/hostels/{role}"+role+";o"+ownerId+";s"+supervisorId); 
		
		List<Hostel> hostels = new ArrayList<Hostel>();
		if(role.equals(IPGConstants.OWNER_CODE)){
			
			hostels = hostelRepository.getHostelsByOwner(ownerId);
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			hostels = hostelRepository.getHostelsBySupervisor(ownerId, supervisorId);
			
		}
		
		hostels.forEach(hostel -> { 
			hostel.setRooms(roomRepository.getRooms(hostel.getId())); 
			hostel.getRooms().forEach(room -> { 
				 room.setBeds(bedRepository.getBeds(hostel.getId(), room.getRoomId())); 
			}); 
			
			hostel.setFacilities(facilitiesRepository.findByHostelId(hostel.getId()));
			
			hostel.setContacts(contactInfoRepository.findByHostelId(hostel.getId()));
		});
		 
		
		logger.info("Out::"+hostels);
		return hostels;
	}
	
	
	@PutMapping("/hostels/{id}")
	public Hostel updateHostel(@PathVariable Long id, @Valid @RequestBody Hostel hostel) {
		logger.info("In::");
		Hostel savedHostel = hostelRepository.save(hostel);
		 
		hostel.getRooms().forEach(room -> {
			 room.setHostelId(savedHostel.getId());
			 Room savedRoom = roomRepository.save(room); 
			room.getBeds().forEach(bed -> {
				bed.setHostelId(savedHostel.getId());
				bed.setRoomId(savedRoom.getRoomId());
				bedRepository.save(bed);
			}); 
		});
		
		hostel.getFacilities().setHostelId(savedHostel.getId());
		facilitiesRepository.save(hostel.getFacilities());
		
		for(ContactInfo contactInfo : hostel.getContacts()){
			contactInfoRepository.save(contactInfo);
		}
		
		logger.info("Out::"+hostel);
		return hostel;
	}
	
	
	@PostMapping("/hostels/{id}/upload/{cat}/{picnum}") 
	public void storeHostelImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat, @PathVariable("picnum") String picnum) throws Exception  {

		logger.info("In::POST::/hostels/{id}/upload/{cat}::uploadHostelImages::" + id + "::" + cat);
		storageService.storeHostelImage(file, cat, id, picnum);
		logger.info("OUT::POST:://hostels/uploadImage/{cat}/{id}::uploadHostelImages::" + id + "::" + cat);

	}

	@GetMapping("/hostels/{id}/retrive/{cat}") 
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws Exception {
		logger.info("IN:://hostels/{id}/retrive/{cat}::retriveHostelImages::" + id + "::" + cat);
		logger.info("OUT:://hostels/{id}/retrive/{cat}::retriveHostelImages::" + id + "::" + cat);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retriveHostelImage(id, cat)));


	}
	

}
