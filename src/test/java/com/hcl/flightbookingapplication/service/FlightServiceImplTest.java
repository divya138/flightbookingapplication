package com.hcl.flightbookingapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.hcl.flightbookingapplication.dao.FlightRepository;
import com.hcl.flightbookingapplication.dto.FlightDto;
import com.hcl.flightbookingapplication.exception.FlightNotFoundException;
import com.hcl.flightbookingapplication.model.Flight;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightServiceImplTest {
	@InjectMocks
	FlightServiceImpl flightServiceImpl;

	@Mock
	FlightRepository flightRepository;

	static Flight flight = null;

	@BeforeClass
	public static void setUp() {
		flight = new Flight();
	}

	@SuppressWarnings("deprecation")
	@Test(expected = FlightNotFoundException.class)
	public void testsearchFlightForPositive() throws FlightNotFoundException {
		List<Flight> flights = new ArrayList();
		Flight flight = new Flight();
		FlightDto flightDto =new FlightDto();
		flight.setDestination("bangalore");
		flight.setOrigin("chennai");
		flights.add(flight);
		Mockito.when(flightRepository.findByOriginAndDestinationAndJourneyDate("chennai", "bangalore", null)).thenReturn(flights);
		List<Flight> flights1 = flightServiceImpl.searchflight(flightDto);
		Assert.assertNotNull(flights1);
		Assert.assertEquals(1, flights1.size());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testsearchbusForNagative() throws FlightNotFoundException {
		List<Flight> flights = new ArrayList();
		Flight flight = new Flight();
		FlightDto flightDto =new FlightDto();
		flightDto.setDestination("bangalore");
		flightDto.setOrigin("chennai");
		flightDto.setJourneyDate(null);
		flights.add(flight);
		Mockito.when(flightRepository.findByOriginAndDestinationAndJourneyDate("chennai", "bangalore", null)).thenReturn(flights);
		List<Flight> flights1 = flightServiceImpl.searchflight(flightDto);
		Assert.assertNotNull(flights1);
		Assert.assertEquals(1, flights1.size());
		
	}
}
