package com.hcl.flightbookingapplication.service;

import java.util.List;

import com.hcl.flightbookingapplication.dto.FlightDto;
import com.hcl.flightbookingapplication.model.Flight;

public interface FlightService {
	public List<Flight> searchflight(FlightDto flightDto);

}
