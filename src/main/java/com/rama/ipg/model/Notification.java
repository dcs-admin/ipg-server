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
@Table(name = "notification")
public class Notification implements Serializable {
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;  
	  
	@Column private  Date  nDate  ;   
	@Column private  String  content ;     
	@Column private  String  type ;
	
	@Column private  Long  notifiedUserId ;
	@Column private  String  notifiedUserName ;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public Long getNotifiedUserId() {
		return notifiedUserId;
	}
	public void setNotifiedUserId(Long notifiedUserId) {
		this.notifiedUserId = notifiedUserId;
	}
	public String getNotifiedUserName() {
		return notifiedUserName;
	}
	public void setNotifiedUserName(String notifiedUserName) {
		this.notifiedUserName = notifiedUserName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [id=");
		builder.append(id);
		builder.append(", nDate=");
		builder.append(nDate);
		builder.append(", content=");
		builder.append(content);
		builder.append(", type=");
		builder.append(type);
		builder.append(", notifiedUserId=");
		builder.append(notifiedUserId);
		builder.append(", notifiedUserName=");
		builder.append(notifiedUserName);
		builder.append("]");
		return builder.toString();
	}
	 
	 
}
