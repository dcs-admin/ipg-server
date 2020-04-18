package com.rama.ipg.model;

 

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "hostel")
public  class Hostel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;  
	
	@Column private String name			;
	@Column private String desc			;
	//  private String rooms		;
	//  private String facilities	; 
	@Column private String goGlobal		;
	@Column private String amIAdmin 	; 
	@Column private String address_line1;
	@Column private String address_line2;
	@Column private String city			;
	@Column private String state 		;
	@Column private String zipcode 		; 
	@Column private Date date			;
	@Column private Date updDate		; 
	@Column private String ownerId		; 
	
	@Column private String supervisorId	;
	 
	
	@Transient
	private List<Room> rooms;
	 

	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	//  private String photos		;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getGoGlobal() {
		return goGlobal;
	}
	public void setGoGlobal(String goGlobal) {
		this.goGlobal = goGlobal;
	}
	public String getAmIAdmin() {
		return amIAdmin;
	}
	public void setAmIAdmin(String amIAdmin) {
		this.amIAdmin = amIAdmin;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		builder.append("Hostel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", goGlobal=");
		builder.append(goGlobal);
		builder.append(", amIAdmin=");
		builder.append(amIAdmin);
		builder.append(", address_line1=");
		builder.append(address_line1);
		builder.append(", address_line2=");
		builder.append(address_line2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", date=");
		builder.append(date);
		builder.append(", updDate=");
		builder.append(updDate);
		builder.append("]");
		return builder.toString();
	}
	
	 
	
}
