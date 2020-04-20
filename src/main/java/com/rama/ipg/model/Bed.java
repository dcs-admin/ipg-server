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
	@Column Long  bedId ;   
	
	@Column private  Long  hostelId ; 
	@Column private  String  hostelName ;   
	@Column private  String  floor   ;     
	@Column private  Long  roomId   ;     
	@Column private  String  roomNumber  ;  
	@Column private  String  bedNumber    ; 
	@Column private  String  sharingType ;  
	@Column private  String  status    ;    
	@Column private  Long  tenantId  ;    
	@Column private  String  tName    ;     
	@Column private  String  bookedDate ;   
	@Column private  String  rent    ;      
	@Column private String ownerId		;
	
	 
	 
	public Long getBedId() {
		return bedId;
	}
	public void setBedId(Long bedId) {
		this.bedId = bedId;
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
	 
	public Long getHostelId() {
		return hostelId;
	}
	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
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
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
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
		builder.append("Bed [bedId=");
		builder.append(bedId);
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
