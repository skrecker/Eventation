package com.shawnkrecker.eventation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscriber {
	
    @Id
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String occupation;
	
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Subscriber(){}
	
	public Subscriber(String userName, String password, String firstName, String lastName, String email){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String uName){
		userName = uName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String first){
		firstName = first;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String last){
		lastName = last;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
}
