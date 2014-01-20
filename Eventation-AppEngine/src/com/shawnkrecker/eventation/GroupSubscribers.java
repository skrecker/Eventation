package com.shawnkrecker.eventation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class GroupSubscribers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String groupID;
	
	private String userID;
	
	private String regID;

	public String getRegID() {
		return regID;
	}

	public void setRegID(String regID) {
		this.regID = regID;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	

}
