package com.hcl.flightbookingapplication.service;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.hcl.flightbookingapplication.dao.BookingRepository;
import com.hcl.flightbookingapplication.dao.FlightRepository;
import com.hcl.flightbookingapplication.dao.PassengerRepository;
import com.hcl.flightbookingapplication.dto.BookingDto;
import com.hcl.flightbookingapplication.dto.BookingsDto;
import com.hcl.flightbookingapplication.dto.TicketDetailsDto;
import com.hcl.flightbookingapplication.exception.InsufficientFundsException;
import com.hcl.flightbookingapplication.exception.NoSeatsAvailableException;
import com.hcl.flightbookingapplication.exception.TicketNotFoundException;
import com.hcl.flightbookingapplication.model.Booking;
import com.hcl.flightbookingapplication.model.Flight;
import com.hcl.flightbookingapplication.model.Passenger;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookingServiceImplTest {
	@InjectMocks
	BookingServiceImpl busBookingServiceImpl;
	@Mock
	BookingRepository busBookingRepo;
	@Mock
	RestTemplate restTemplate;

	@Mock
	FlightRepository busRepo;
	@Mock
	PassengerRepository userRepo;

	@Test(expected = NoSeatsAvailableException.class)
	public void bookBusServiceTestbus() throws JSONException, NoSeatsAvailableException {
		BookingsDto bookingDto = new BookingsDto();
		Flight flight = new Flight();
		BookingDto booking = new BookingDto();
		Booking b = new Booking();
		Passenger usr = new Passenger();
		usr.setEmail("bhavan");
		usr.setPassengerId(2);
		usr.setMobileNumber(9989787656l);
		bookingDto.setFlightNumber(123);
		booking.setFlightName("indigo");
		booking.setFlightNumber(123);
		booking.setNoOfSeats(3);
		bookingDto.setNumberOfSeats(3);
		flight.setAvailableSeats(10);
		b.setPassenger(usr);
		b.setFlight(flight);
		bookingDto.setNumberOfSeats(flight.getAvailableSeats() - booking.getNoOfSeats());
		Mockito.when(userRepo.getOne(usr.getPassengerId())).thenReturn(usr);
		busBookingServiceImpl.bookTheTicket(bookingDto, 8878989087l);
		Assert.assertNotNull(usr);
		Assert.assertEquals(booking.getFlightNumber(), bookingDto.getFlightNumber());

	}

	@Test
	public void testForBooking() {
		BookingsDto bookingDto = new BookingsDto();
		bookingDto.setNumberOfSeats(2);
		bookingDto.setPassengerId(12);
		bookingDto.setFlightNumber(123);
		Booking booking = new Booking();
		Passenger passenger = new Passenger();
		BeanUtils.copyProperties(bookingDto, booking);
		Flight f = new Flight();
		f.setFare(100);
		f.setFlightNumber(123);
		passenger.setEmail("bhavani@gmail.com");
		passenger.setPassengerId(2);
		Mockito.when(userRepo.findById(2)).thenReturn(Optional.of(passenger));
		Mockito.when(busRepo.findById(123)).thenReturn(Optional.of(f));
		busRepo.save(f);
		booking.setAmmount(f.getFare() * bookingDto.getNumberOfSeats());
		booking.setPassenger(passenger);
		;
		booking.setFlight(f);
		Assert.assertNotNull(f);

	}

	@Test(expected = NullPointerException.class)
	public void testForBooking2() throws NoSeatsAvailableException, InsufficientFundsException, JSONException {
		BookingsDto bookingDto = new BookingsDto();
		bookingDto.setNumberOfSeats(2);
		bookingDto.setPassengerId(12);
		bookingDto.setFlightNumber(123);

		Booking booking = new Booking();
		Booking booking1 = new Booking();
		Passenger passenger = new Passenger();
		BeanUtils.copyProperties(bookingDto, booking);
		Flight f = new Flight();
		f.setFare(100);
		f.setFlightNumber(123);
		bookingDto.setNumberOfSeats(f.getAvailableSeats() - bookingDto.getNumberOfSeats());
		passenger.setEmail("bhavani@gmail.com");
		passenger.setPassengerId(2);
		Mockito.when(userRepo.findById(2)).thenReturn(Optional.of(passenger));
		Mockito.when(busRepo.findById(123)).thenReturn(Optional.of(f));
		busRepo.save(f);
		booking.setAmmount(f.getFare() * bookingDto.getNumberOfSeats());
		booking.setPassenger(passenger);
		;
		booking.setFlight(f);
		booking.setNoOfSeats(bookingDto.getNumberOfSeats());
		Mockito.when(busBookingRepo.save(booking)).thenReturn(booking1);
		BeanUtils.copyProperties(bookingDto, booking1);
		Assert.assertNotNull(f);
		busBookingServiceImpl.bookTheTicket(bookingDto, 6778798L);

	}
	@Test(expected=TicketNotFoundException.class)
	public void testForTicketDetails() {
		TicketDetailsDto dto=new TicketDetailsDto();
		Booking booking2=new Booking();
		booking2.setTicketNumber(123);
		dto.setNoOfSeats(2);
		dto.setTicketNumber(321);
		Optional<Booking> booking=busBookingRepo.findByTicketNumber(booking2.getTicketNumber());
		Mockito.when(busBookingRepo.findByTicketNumber(booking2.getTicketNumber())).thenReturn(booking);
		Booking dto2=busBookingServiceImpl.getTicketById(booking2.getTicketNumber());
		Assert.assertNotNull(dto2);
		
		
	}
	@Test
	public void testBookingListForPositive() {
		Booking booking=new Booking();
		Flight flight=new Flight();
		Passenger passenger=new Passenger();
		flight.setFlightName("indigo");
		passenger.setEmail("bhavani@gmail.com");
		passenger.setFirstName("bhavani");
		booking.setTicketNumber(123);
		booking.setNoOfSeats(2);
		booking.setPassenger(passenger);
		booking.setFlight(flight);
		Mockito.when(busBookingRepo.findByTicketNumber(123)).thenReturn(Optional.of(booking));
		Booking dto=busBookingServiceImpl.getTicketById(123);
		Assert.assertNotNull(dto);
		
	}

	@Test(expected = NullPointerException.class)
	public void TestforgetTheGetNegtive() {

		Booking route = new Booking();
		route.setTicketNumber(123);
		route.setNoOfSeats(5);

		Optional<Booking> routedto = Optional.ofNullable(new Booking());

		Mockito.when(busBookingRepo.findByTicketNumber(Mockito.anyInt())).thenReturn(routedto);

		Booking result = busBookingServiceImpl.getTicketById(-577);
		Assert.assertNotNull(route);
		Assert.assertNotNull(result);
	}


	@Test
	public void booResponseEntitypos() throws JSONException {
		BookingsDto bookingDto = new BookingsDto();
try {
		String uri = "http://ACCOUNT-SERVICE/transaction";
		double sum = 5;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("accountNumber", 8989l);
		request.put("amount", sum);
		request.put("banificiaryAccountNo", 6666l);

		HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

		ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

	}
    catch (InsufficientFundsException e) {
		
}
	}
}
