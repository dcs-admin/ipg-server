package com.rama.ipg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense implements Serializable {
	

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  eid ;  

	@Column private  Date  eDate ;         
	@Column private  String  amount  ;       
	@Column private  String  comments  ;     
	@Column private  String  category ;      

	@Column private  String  hostelId ;      
	@Column private  String  hostelName ;  
	@Column private String ownerId		;
	
	
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	 
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [eid=");
		builder.append(eid);
		builder.append(", eDate=");
		builder.append(eDate);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", category=");
		builder.append(category);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
