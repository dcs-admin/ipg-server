/**
 * 
 */
package com.rama.ipg.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Mine
 *
 */
@Entity
@Table(name = "room")
public class Room {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;  
	
	@Column private String  roomId 		;
	@Column private String  roomNumber;
	@Column private String  roomType	;
	@Column private long  roomRent;	
	@Column private String  roomDesc ;
	@Column private String  floor 	;
	
	@Column private long  hostelId 	;
	
	@Transient
	private List<Bed> beds;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public long getHostelId() {
		return hostelId;
	}

	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(long roomRent) {
		this.roomRent = roomRent;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public List<Bed> getBeds() {
		return beds;
	}

	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [roomId=");
		builder.append(roomId);
		builder.append(", roomNumber=");
		builder.append(roomNumber);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", roomDesc=");
		builder.append(roomDesc);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", beds=");
		builder.append(beds);
		builder.append("]");
		return builder.toString();
	}
	
	



}
