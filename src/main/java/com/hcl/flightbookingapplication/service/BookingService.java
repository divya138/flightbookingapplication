package com.hcl.flightbookingapplication.service;

import org.json.JSONException;
import com.hcl.flightbookingapplication.dto.BookingsDto;
import com.hcl.flightbookingapplication.model.Booking;

public interface BookingService {

	public Booking bookTheTicket(BookingsDto bookingsDto, long passengerAccountNumber)
			throws JSONException;
	public Booking getTicketById(int id);

}
