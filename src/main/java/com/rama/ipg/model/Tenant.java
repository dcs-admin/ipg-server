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

/**
 * @author Mine
 *
 */
@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {
	

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
	@Column private  String phoneNumber;
	@Column private  String aadhar;
	@Column private  String emergency;
	@Column private  String email;
	@Column private  String gender; 
	@Column private  Date date;
	@Column private  String photo; 
	@Column private  String hostelId;
	@Column private  String hostelName; 
	@Column private  String rentType;
	@Column private  String rentMethod;
	@Column private  String rentAmount;
	@Column private  String advanceAmount;
	@Column private  String eSignature;
	@Column private String ownerId;
	
	@Column private String password	;
	
	@Column private String status ="A"	;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getHostelId() {
		return hostelId;
	}
	public void setHostelId(String hostelId) {
		this.hostelId = hostelId;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getRentType() {
		return rentType;
	}
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	public String getRentMethod() {
		return rentMethod;
	}
	public void setRentMethod(String rentMethod) {
		this.rentMethod = rentMethod;
	}
	public String getRentAmount() {
		return rentAmount;
	}
	public void setRentAmount(String rentAmount) {
		this.rentAmount = rentAmount;
	}
	public String getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public String geteSignature() {
		return eSignature;
	}
	public void seteSignature(String eSignature) {
		this.eSignature = eSignature;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	} 
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", desc=");
		builder.append(desc);
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
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", aadhar=");
		builder.append(aadhar);
		builder.append(", emergency=");
		builder.append(emergency);
		builder.append(", email=");
		builder.append(email);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", date=");
		builder.append(date);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", rentType=");
		builder.append(rentType);
		builder.append(", rentMethod=");
		builder.append(rentMethod);
		builder.append(", rentAmount=");
		builder.append(rentAmount);
		builder.append(", advanceAmount=");
		builder.append(advanceAmount);
		builder.append(", eSignature=");
		builder.append(eSignature);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", password=");
		builder.append("*****");
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	 

}
