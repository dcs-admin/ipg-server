/**
 * 
 */
package com.rama.ipg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mine
 *
 */
public class IPGRetObj {

	
	private Object retMsg;
	private List<Object> retMsgs;
	
	public IPGRetObj(){
		this.retMsg="";
		this.retMsgs = new ArrayList<Object>();
	}

	public Object getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(Object retMsg) {
		this.retMsg = retMsg;
	}

	public List<Object> getRetMsgs() {
		return retMsgs;
	}

	public void setRetMsgs(List<Object> retMsgs) {
		this.retMsgs = retMsgs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IPGRetObj [retMsg=");
		builder.append(retMsg);
		builder.append(", retMsgs=");
		builder.append(retMsgs);
		builder.append("]");
		return builder.toString();
	}
	
	
}
