/**
 * 
 */
package com.rama.ipg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Mine
 *
 */
@Entity
@Table(name = "contactInfo")
public class ContactInfo implements Serializable {
	

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;  
	 
	@Column private  String name;
	@Column private  String desc; 
	@Column private  String address_line1;
	@Column private  String address_line2;
	@Column private  String city;
	@Column private  String state;
	@Column private  String zipcode; 
	@Column private  Long phoneNumber;
	@Column private  String aadhar;
	@Column private  String emergency;
	@Column private  String email;
	@Column private  String gender; 
	@Column private  Date date; 
	@Column private  Long hostelId;
	@Column private  String hostelName; 
	@Column private  String designation;
	
	@Column private Long ownerId;
	@Column private Long supervisorId;
	
	@Column @JsonIgnore private String password	;
	
	@Column private String status ="A"	;

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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactInfo [id=").append(id).append(", name=").append(name).append(", desc=").append(desc)
				.append(", address_line1=").append(address_line1).append(", address_line2=").append(address_line2)
				.append(", city=").append(city).append(", state=").append(state).append(", zipcode=").append(zipcode)
				.append(", phoneNumber=").append(phoneNumber).append(", aadhar=").append(aadhar).append(", emergency=")
				.append(emergency).append(", email=").append(email).append(", gender=").append(gender).append(", date=")
				.append(date).append(", hostelId=").append(hostelId).append(", hostelName=").append(hostelName)
				.append(", designation=").append(designation).append(", ownerId=").append(ownerId)
				.append(", supervisorId=").append(supervisorId).append(", password=").append(password)
				.append(", status=").append(status).append("]");
		return builder.toString();
	}
	
	
	
	 
}
