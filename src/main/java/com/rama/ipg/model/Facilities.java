/**
 * 
 */
package com.rama.ipg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mine
 *
 */

@Entity
@Table(name = "facilities")
public class Facilities  implements Serializable{
	
	@Id   
	@Column private Long hostelId	; 
		 
	@Column private String tv 	="N";		 
	@Column private String fridge="N";		 
	@Column private String ac 	="N";		 
	@Column private String minWater	="Y";	 
	@Column private String powerBackup 	="N"; 
	@Column private String dhobi="Y";		 
	@Column private String security ="N";	 
	@Column private String gym	="N";		 
	@Column private String ref	="N";		 
	@Column private String parking ="N";		 
	@Column private String wifi ="Y";
	
	
	public Long getHostelId() {
		return hostelId;
	}
	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}
	public String getTv() {
		return tv;
	}
	public void setTv(String tv) {
		this.tv = tv;
	}
	public String getFridge() {
		return fridge;
	}
	public void setFridge(String fridge) {
		this.fridge = fridge;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getMinWater() {
		return minWater;
	}
	public void setMinWater(String minWater) {
		this.minWater = minWater;
	}
	public String getPowerBackup() {
		return powerBackup;
	}
	public void setPowerBackup(String powerBackup) {
		this.powerBackup = powerBackup;
	}
	public String getDhobi() {
		return dhobi;
	}
	public void setDhobi(String dhobi) {
		this.dhobi = dhobi;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getGym() {
		return gym;
	}
	public void setGym(String gym) {
		this.gym = gym;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Facilities [hostelId=");
		builder.append(hostelId);
		builder.append(", tv=");
		builder.append(tv);
		builder.append(", fridge=");
		builder.append(fridge);
		builder.append(", ac=");
		builder.append(ac);
		builder.append(", minWater=");
		builder.append(minWater);
		builder.append(", powerBackup=");
		builder.append(powerBackup);
		builder.append(", dhobi=");
		builder.append(dhobi);
		builder.append(", security=");
		builder.append(security);
		builder.append(", gym=");
		builder.append(gym);
		builder.append(", ref=");
		builder.append(ref);
		builder.append(", parking=");
		builder.append(parking);
		builder.append(", wifi=");
		builder.append(wifi);
		builder.append("]");
		return builder.toString();
	}
	
	
}
