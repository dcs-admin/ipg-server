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
@Table(name = "payment")
public class Payment implements Serializable {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  pid ;  
	
	
	@Column private  String  tenantId   ;   
	@Column private  String  tName  ;       
	@Column private  Date  paidDate  ;    
	@Column private  long  amount   ;     
	@Column private  long  pendingAmount ;
	@Column private  String  comments ;     
	@Column private  String  rentMethod  ;  

	@Column private  String  hostelId  ;    
	@Column private  String  hostelName; 
	@Column private String ownerId		;
	
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(long pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRentMethod() {
		return rentMethod;
	}
	public void setRentMethod(String rentMethod) {
		this.rentMethod = rentMethod;
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
		builder.append("Payment [pid=");
		builder.append(pid);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", tName=");
		builder.append(tName);
		builder.append(", paidDate=");
		builder.append(paidDate);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", pendingAmount=");
		builder.append(pendingAmount);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", rentMethod=");
		builder.append(rentMethod);
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
