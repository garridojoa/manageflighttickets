package com.jg.manageflighttickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jg.manageflighttickets.model.FlightTicket;

public interface FlightTicketRepository  extends JpaRepository<FlightTicket, Long> {

}
