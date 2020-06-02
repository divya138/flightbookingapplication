package com.hcl.flightbookingapplication.controller;


import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hcl.flightbookingapplication.dto.BookingsDto;
import com.hcl.flightbookingapplication.exception.InsufficientFundsException;
import com.hcl.flightbookingapplication.exception.NoSeatsAvailableException;
import com.hcl.flightbookingapplication.model.Booking;
import com.hcl.flightbookingapplication.service.BookingServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookingControllerTest {
	@InjectMocks
	BookingController busBookingController;
	
	@Mock
    BookingServiceImpl busBookingService;
	static Booking dto=new Booking();
	static Booking booking=null;
	@BeforeClass
	public static void setUp() {
		booking=new Booking();
		booking.setTicketNumber(23335);
	}
	@Test
	public void testTicketbookingpistive() throws NoSeatsAvailableException, InsufficientFundsException, JSONException {
		Booking bookingDto =new Booking();
		//bookingDto.setFlightNumber(123);
		bookingDto.setNoOfSeats(3);
		BookingsDto dto=new BookingsDto();
		dto.setNumberOfSeats(3);
		dto.setPassengerId(1);
		Mockito.when(busBookingService.bookTheTicket(dto, 78786687L)).thenReturn(bookingDto);
		ResponseEntity<Booking> b=busBookingController.bookingtheTicket(dto, 5786877868L);
		
		Assert.assertNotNull(b);
		
	}
	
	@Test
	public void testTicketbookingposnew() throws NoSeatsAvailableException, InsufficientFundsException, JSONException {
		Booking bookingDto =new Booking();
		Booking booking =new Booking();
		bookingDto.setNoOfSeats(3);
		BookingsDto bookingsDto =new BookingsDto();
		bookingsDto.setFlightNumber(123);
		Mockito.when(busBookingService.bookTheTicket(bookingsDto, 76878L)).thenReturn(bookingDto);
		Assert.assertNotNull(booking);
		ResponseEntity<Booking> b=busBookingController.bookingtheTicket(bookingsDto, 5786877868L);
	
		
		Assert.assertEquals(HttpStatus.CREATED, b.getStatusCode());
		
	}

	@Test
	public void testgetTicketNumberpositive() {
		Mockito.when(busBookingService.getTicketById(booking.getTicketNumber())).thenReturn(dto);
		ResponseEntity<Booking> p1 = busBookingController.getTicketDetails(booking.getTicketNumber());
		Assert.assertNotNull(p1);
		Assert.assertNotNull(dto);
		Assert.assertEquals(HttpStatus.OK, p1.getStatusCode());

	}
	@Test
	public void testgetTicketNumberpositiveNegtive() {
		Mockito.when(busBookingService.getTicketById(booking.getTicketNumber())).thenReturn(dto);
		ResponseEntity<Booking> p1 = busBookingController.getTicketDetails(booking.getTicketNumber());
		Assert.assertNotNull(p1);
	
	}
	
	@AfterClass
	public static void tearDown() {
		booking=null;
		
	}
	@Test
	public void testTicketbooking() throws NoSeatsAvailableException, InsufficientFundsException, JSONException {
		Booking bookingDto =new Booking();
		Booking booking =new Booking();
		//bookingDto.setFlightNumber(123);;
		bookingDto.setNoOfSeats(3);
		BookingsDto dto=new BookingsDto();
		dto.setNumberOfSeats(3);
		dto.setPassengerId(1);
		Mockito.when(busBookingService.bookTheTicket(dto, 78786687L)).thenReturn(bookingDto);
		ResponseEntity<Booking> b=busBookingController.bookingtheTicket(dto, 5786877868L);
		
		Assert.assertNotNull(b);
		
	}
	
	@Test
	public void testTicketbookingpos() throws NoSeatsAvailableException, InsufficientFundsException, JSONException {
		Booking bookingDto =new Booking();
		Booking booking =new Booking();
		//bookingDto.setFlightNumber(123);
		bookingDto.setNoOfSeats(3);
		BookingsDto bookingsDto =new BookingsDto();
		bookingsDto.setFlightNumber(123);
		Mockito.when(busBookingService.bookTheTicket(bookingsDto, 76878L)).thenReturn(bookingDto);
		Assert.assertNotNull(booking);
		ResponseEntity<Booking> b=busBookingController.bookingtheTicket(bookingsDto, 5786877868L);
	
		
		Assert.assertEquals(HttpStatus.CREATED, b.getStatusCode());
		
	}

}
