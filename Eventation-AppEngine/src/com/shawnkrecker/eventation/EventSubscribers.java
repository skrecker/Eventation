package com.shawnkrecker.eventation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventSubscribers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ESID;
	
	private int eventID;
	
	private int userID;

	public int getESID() {
		return ESID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
}
