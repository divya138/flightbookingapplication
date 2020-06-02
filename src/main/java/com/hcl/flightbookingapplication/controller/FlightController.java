package com.hcl.flightbookingapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.flightbookingapplication.dto.FlightDto;
import com.hcl.flightbookingapplication.model.Flight;
import com.hcl.flightbookingapplication.service.FlightService;

@RestController
public class FlightController {
	@Autowired
	FlightService flightService;

	@PostMapping(value = "/flights")
	public ResponseEntity<List<Flight>> searchflight(@RequestBody FlightDto flightDto){
		List<Flight> flights = flightService.searchflight(flightDto);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);

	}

}
