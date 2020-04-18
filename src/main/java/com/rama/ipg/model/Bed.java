/**
 * 
 */
package com.rama.ipg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mine
 *
 */

@Entity
@Table(name = "bed")
public class Bed  implements Serializable{
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;   
	
	@Column private  long  hostelId ; 
	@Column private  String  hostelName ;   
	@Column private  String  floor   ;     
	@Column private  long  roomId   ;     
	@Column private  String  roomNumber  ;  
	@Column private  String  bedNumber    ; 
	@Column private  String  sharingType ;  
	@Column private  String  status    ;    
	@Column private  String  tenantId  ;    
	@Column private  String  tName    ;     
	@Column private  String  bookedDate ;   
	@Column private  String  rent    ;      
	@Column private String ownerId		;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	 
	public long getHostelId() {
		return hostelId;
	}
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public String getSharingType() {
		return sharingType;
	}
	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getBookedDate() {
		return bookedDate;
	}
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bed [id=");
		builder.append(id);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", roomId=");
		builder.append(roomId);
		builder.append(", roomNumber=");
		builder.append(roomNumber);
		builder.append(", bedNumber=");
		builder.append(bedNumber);
		builder.append(", sharingType=");
		builder.append(sharingType);
		builder.append(", status=");
		builder.append(status);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", tName=");
		builder.append(tName);
		builder.append(", bookedDate=");
		builder.append(bookedDate);
		builder.append(", rent=");
		builder.append(rent);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append("]");
		return builder.toString();
	} 
	
	
	

}
