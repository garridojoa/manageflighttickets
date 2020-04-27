package com.jg.manageflighttickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jg.manageflighttickets.model.Passenger;

public interface PassengerRepository  extends JpaRepository<Passenger, Long> {
	  Passenger findByFullName(String fullName);
}
