/**
 * 
 */
package com.rama.ipg.model;

/**
 * @author Mine
 *
 */
public class TenantWrapper {
	
	private Tenant tenant;
	private Bed bed;
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Bed getBed() {
		return bed;
	}
	public void setBed(Bed bed) {
		this.bed = bed;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantWrapper [tenant=");
		builder.append(tenant);
		builder.append(", bed=");
		builder.append(bed);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
