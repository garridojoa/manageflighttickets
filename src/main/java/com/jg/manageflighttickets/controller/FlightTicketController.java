package com.jg.manageflighttickets.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jg.manageflighttickets.exceptions.BadRequestException;
import com.jg.manageflighttickets.exceptions.ResourceNotFoundException;
import com.jg.manageflighttickets.model.FlightTicket;
import com.jg.manageflighttickets.model.Passenger;
import com.jg.manageflighttickets.repository.FlightTicketRepository;
import com.jg.manageflighttickets.repository.PassengerRepository;

@RestController
@RequestMapping("/flighttickets")
public class FlightTicketController {

	@Autowired
	FlightTicketRepository ftRepo;
	@Autowired
	PassengerRepository pRepo;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public FlightTicket getFlightTickets(@PathVariable long id) {
		if (id > 0) {
			return ftRepo.findById(id).map(flightTicket -> {
				return flightTicket;
			}).orElseThrow(() -> new ResourceNotFoundException("FlightTicket not found with id " + String.valueOf(id)));
		} else {
			throw new BadRequestException("FlightTicket retrieve failed. Not a valid itinerary id: " + String.valueOf(id));
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public FlightTicket createFlightTicket(@Valid @RequestBody FlightTicket flightTicket) {
		Set<Passenger> savedPassengerList = new HashSet<Passenger>();
		for (Passenger passenger : flightTicket.getPassengers()) {
			if (passenger.getFullName() == null || passenger.getFullName().isEmpty() 
					|| passenger.getBirthDate() == null || passenger.getFullName().isEmpty()) {
				throw new BadRequestException("Faile adding new passenger: Every Passenger should have Full Name and BirthDay.");
			}
			Passenger savedPassenger = pRepo.findByFullName(passenger.getFullName());
			if (savedPassenger == null) {
				try {
					savedPassenger = pRepo.save(passenger);
				} catch (Exception e) {
					throw new BadRequestException("Faile adding new passenger: " + e.getMessage());
				}
			}
			savedPassengerList.add(savedPassenger);
		}
		if (flightTicket.getDepartureDate() == null) {
			throw new BadRequestException("Faile adding flightTickect: should have valid DepartureDate");
		}
		if (flightTicket.getArrivalDate() == null) {
			throw new BadRequestException("Faile adding flightTickect: should have valid ArrivalDate");
		}
		if (flightTicket.getOriginCity() == null || flightTicket.getOriginCity().isEmpty()) {
			throw new BadRequestException("Faile adding flightTickect: should have valid OriginCity.");
		}
		if (flightTicket.getDestinationCity() == null || flightTicket.getDestinationCity().isEmpty()) {
			throw new BadRequestException("Faile adding flightTickect: should have valid DestinationCity.");
		}
		if (flightTicket.getPrice() == null) {
			throw new BadRequestException("Faile adding flightTickect: should have valid Price.");
		}
		if (flightTicket.getDepartureTime() == null) {
			throw new BadRequestException("Faile adding flightTickect: should have valid DepartureTime.");
		}
		if (flightTicket.getArrivalTime() == null) {
			throw new BadRequestException("Faile adding flightTickect: should have valid ArrivalTime.");
		}
		flightTicket.setPassengers(savedPassengerList);
		FlightTicket addedflightTicket = null;
		try {
			addedflightTicket = ftRepo.save(flightTicket);
		} catch (Exception e) {
			throw new BadRequestException("FlightTicket add failed: " + e.getMessage());
		}
		return addedflightTicket;
	}
	
	/* 
	 * This is a valid REST add method request body for test purpose:
		{ 
		 "departureDate":"2020-04-26", 
		 "arrivalDate":"2020-04-26", 
		 "originCity":"Buenos Aires", 
		 "destinationCity":"Ushuaia", 
		 "passengers":[ 
		 	{"fullName":"Jane Doe", 
		 	 "birthDate":"1999-12-31" 
		 	}, 
		 	{"fullName":"John Smith", 
		 	 "birthDate":"1999-12-30" 
		 	} 
		 	], 
		 "price":"1000.00", 
		 "departureTime":"10:00:00", 
		 "arrivalTime":"13:00:00"
		 }
	 */
	
}
