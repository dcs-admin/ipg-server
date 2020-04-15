/**
 * 
 */
package com.rama.ipg.model;

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
@Table(name = "requirement")
public class Requirement {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long  id ; 
	@Column private String name;
    @Column private String mobileNumber;
    @Column private String comments;
    @Column private Date updDate = new Date();
    
    
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Requirement [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", updDate=");
		builder.append(updDate);
		builder.append("]");
		return builder.toString();
	}
    
    

}
