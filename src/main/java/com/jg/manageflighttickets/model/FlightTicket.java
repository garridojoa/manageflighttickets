package com.jg.manageflighttickets.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * FlightTicket class
 * @author jgarrido
 */
@Entity
public class FlightTicket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long itineraryId;
	@Column(nullable = false) 
	private Date departureDate;
	@Column(nullable = false) 
	private Date arrivalDate;
	// Note: In a real application should create an entity for City, but to simplify this exercise decided not do it.
	@Column(nullable = false) 
	private String originCity;
	@Column(nullable = false) 
	private String destinationCity;
    @ManyToMany(fetch=FetchType.EAGER)
    @Column(nullable = false) 
    @JoinTable(name = "flightticket_passenger", joinColumns = { @JoinColumn(name = "itineraryId") }, inverseJoinColumns = {
			@JoinColumn(name = "passengerId") })
    private Set<Passenger> passengers = new HashSet<Passenger>();
	@Column(nullable = false) 
	private boolean hasLuggageStorage;
	@Column(nullable = false) 
	private Float price;
	@Column(nullable = false) 
	private Time departureTime;
	@Column(nullable = false) 
	private Time arrivalTime;
	
	public FlightTicket() {
    }

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public Set<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passenger> passengers) {
		this.passengers = passengers;
	}

	public boolean isHasLuggageStorage() {
		return hasLuggageStorage;
	}

	public void setHasLuggageStorage(boolean hasLuggageStorage) {
		this.hasLuggageStorage = hasLuggageStorage;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}
