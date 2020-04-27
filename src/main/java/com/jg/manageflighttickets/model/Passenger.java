package com.jg.manageflighttickets.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Passenger class
 * @author jgarrido
 */
@Entity
public class Passenger implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long passengerId;
	@Column(nullable = false) 
	private String fullName;
	@Column(nullable = false) 
	private Date birthDate;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
