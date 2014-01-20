package com.shawnkrecker.eventation;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Group {

	@Id
	private String groupName;
	
	private String adminName;
	
	private String groupDescription;
	
	private Integer numSubscribers;
	
	public Group(){
		
	}
	
	public Group(String groupName, String adminName, String groupDescription, int numSubscribers){
		this.groupName = groupName;
		this.adminName = adminName;
		this.groupDescription = groupDescription;
		this.numSubscribers = numSubscribers;
	}
	
	
	public String getGroupName(){
		return groupName;
	}
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getGroupDescription(){
		return groupDescription;
	}
	
	public void setGroupDescription(String groupDescription){
		this.groupDescription = groupDescription;
	}
	
	public Integer numSubscribers(){
		return numSubscribers;
	}
	
	public void setnumSubscribers(Integer numSubscribers){
		this.numSubscribers = numSubscribers;
	}
	
	
	
	
}
