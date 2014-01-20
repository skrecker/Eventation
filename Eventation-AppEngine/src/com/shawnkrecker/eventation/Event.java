package com.shawnkrecker.eventation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;


@Entity
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String groupName;

	private String eventName;
	
	private String eventDescription;
	
	private Date eventDate;
	
	private Integer attendingCount;
	
	public Event(){
		attendingCount = 0;
	}
	
	public Event(String groupName, String eventName, Date eventDate, String eventDescription){
		this.groupName = groupName;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventDescription = eventDescription;
		this.attendingCount = 0;
	}
	
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getEventName(){
		return eventName;
	}

	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public Date getEventDate(){
		return eventDate;
	}
	
	public void setEventDate(Date eventDate){
		this.eventDate = eventDate;
	}
	
	
	public String getEventDescription(){
		return eventDescription;
	}
	
	public void setEventDescription(String eventDescription){
		this.eventDescription = eventDescription;
	}
	
	public Integer getAttendingCount(){
		return attendingCount;
	}
	
	public void setAttendingCount(Integer attendingCount) {
		this.attendingCount = attendingCount;
	}
}
