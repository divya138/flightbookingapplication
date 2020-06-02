package com.hcl.flightbookingapplication.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import com.hcl.flightbookingapplication.dto.FlightDto;
import com.hcl.flightbookingapplication.exception.FlightNotFoundException;
import com.hcl.flightbookingapplication.model.Flight;
import com.hcl.flightbookingapplication.service.FlightServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightControllerTest {
	@InjectMocks
	FlightController flightController;

	@Mock
	FlightServiceImpl flightService;

	@Test
	public void testFindByOriginAndDestinationAndDateForPostive() throws FlightNotFoundException {

		List<Flight> flights = new ArrayList<Flight>();
		FlightDto flightDto = new FlightDto();
		flightDto.setOrigin("srikakulam");
		flightDto.setDestination("hyderabad");
		Mockito.when(flightService.searchflight(flightDto)).thenReturn(flights);
		ResponseEntity<List<Flight>> flight1 = flightController.searchflight(flightDto);
		Assert.assertNotNull(flight1);
		Assert.assertEquals(flightDto.getDestination(), "hyderabad");
	}

	@Test(expected = Exception.class)
	public void testFindBySourceAndDestinationAndDateForExce() throws FlightNotFoundException {

		List<Flight> buses = new ArrayList<Flight>();
		FlightDto flightDto = new FlightDto();
		flightDto.setOrigin("srikakulam");
		flightDto.setDestination("hyderabad");
		Mockito.when(flightService.searchflight(flightDto)).thenThrow(Exception.class);
		ResponseEntity<List<Flight>> flight1 = flightController.searchflight(flightDto);
		Assert.assertNotNull(flight1);
		Assert.assertEquals(flightDto.getDestination(), "vizag");

	}

	@Test
	public void testFindBySourceAndDestinationAndDateForNegative() throws FlightNotFoundException {

		List<Flight> buses = new ArrayList<Flight>();
		FlightDto flightDto = new FlightDto();
		flightDto.setOrigin("srikakulam");
		flightDto.setDestination("hyderabad");
		flightDto.setOrigin("hyderabad");
		flightDto.setDestination("chennai");
		Mockito.when(flightService.searchflight(flightDto)).thenReturn(buses);
		ResponseEntity<List<Flight>> flight1 = flightController.searchflight(flightDto);
		Assert.assertNotNull(flight1);
		Assert.assertEquals(flightDto.getDestination(), "chennai");

	}
}
