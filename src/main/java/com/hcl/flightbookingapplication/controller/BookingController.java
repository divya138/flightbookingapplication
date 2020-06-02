package com.hcl.flightbookingapplication.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.flightbookingapplication.dto.BookingDto;
import com.hcl.flightbookingapplication.dto.BookingsDto;
import com.hcl.flightbookingapplication.model.Booking;
import com.hcl.flightbookingapplication.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	Booking booking1 = new Booking();

	@PostMapping("/booking")
	public ResponseEntity<Booking> bookingtheTicket(@RequestBody BookingsDto bookingsDto,
			@RequestParam long userAccountNumber) throws JSONException {
		booking1 = bookingService.bookTheTicket(bookingsDto, userAccountNumber);
		return new ResponseEntity<Booking>(booking1, HttpStatus.CREATED);
	}
	@GetMapping("/ticket/{ticketNumber}")
	public ResponseEntity<Booking> getTicketDetails(@PathVariable int ticketNumber) {
		Booking dto = bookingService.getTicketById(ticketNumber);
		return new ResponseEntity<Booking>(dto, HttpStatus.OK);

	}


}
