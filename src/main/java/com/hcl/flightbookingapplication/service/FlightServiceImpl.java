package com.hcl.flightbookingapplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.flightbookingapplication.dao.FlightRepository;
import com.hcl.flightbookingapplication.dto.FlightDto;
import com.hcl.flightbookingapplication.exception.FlightNotFoundException;
import com.hcl.flightbookingapplication.model.Flight;

@Service
public class FlightServiceImpl implements FlightService {
	@Autowired
	FlightRepository flightRepository;

	@Override
	public List<Flight> searchflight(FlightDto flightDto)  {
		List<Flight> flights = flightRepository.findByOriginAndDestinationAndJourneyDate(flightDto.getOrigin(),
				flightDto.getDestination(), flightDto.getJourneyDate());
		if (flights.isEmpty()) {
			throw new FlightNotFoundException("flight is not available");
		}
		return flights;
	}

}
