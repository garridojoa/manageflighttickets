package com.jg.manageflighttickets.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlightTicketControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	// Note: This tests should be run for Test Runner JUNIT 4
	@Test
	public void verifyInvalidGetFlightTicketByArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/flighttickets/r").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void verifyInvalidGetFlightTicketById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/flighttickets/0").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest()).andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveFlightTicket() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/restaurants/add")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content("\n" + 
	        		"    \"departureDate\": \"2020-04-26\",\n" + 
	        		"    \"arrivalDate\": \"2020-04-26\",\n" + 
	        		"    \"originCity\": \"Buenos Aires\",\n" + 
	        		"    \"destinationCity\": \"Ushuaia\",\n" + 
	        		"    \"passengers\": [\n" + 
	        		"        {\n" + 
	        		"            \"fullName\": \"Jane Doe\",\n" + 
	        		"            \"birthDate\": \"1999-12-31\"\n" + 
	        		"        },\n" + 
	        		"        {\n" + 
	        		"            \"fullName\": \"John Smith\",\n" + 
	        		"            \"birthDate\": \"1999-12-30\"\n" + 
	        		"        }\n" + 
	        		"    ],\n" + 
	        		"    \"hasLuggageStorage\": false,\n" + 
	        		"    \"price\": 1000,\n" + 
	        		"    \"departureTime\": \"10:00:00\",\n" + 
	        		"    \"arrivalTime\": \"13:00:00\"\n" + 
	        		"}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(404))		
			.andDo(print());
	}
}
