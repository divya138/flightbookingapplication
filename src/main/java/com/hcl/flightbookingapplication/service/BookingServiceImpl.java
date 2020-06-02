package com.hcl.flightbookingapplication.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hcl.flightbookingapplication.dao.BookingRepository;
import com.hcl.flightbookingapplication.dao.FlightRepository;
import com.hcl.flightbookingapplication.dao.PassengerRepository;
import com.hcl.flightbookingapplication.dto.BookingDto;
import com.hcl.flightbookingapplication.dto.BookingsDto;
import com.hcl.flightbookingapplication.exception.InsufficientFundsException;
import com.hcl.flightbookingapplication.exception.NoSeatsAvailableException;
import com.hcl.flightbookingapplication.exception.TicketNotFoundException;
import com.hcl.flightbookingapplication.model.Booking;
import com.hcl.flightbookingapplication.model.Flight;
import com.hcl.flightbookingapplication.model.Passenger;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	RestTemplate restTemplate;

	BookingDto dto = new BookingDto();

	@Override
	public Booking bookTheTicket(BookingsDto bookingsDto, long passengerAccountNumber)
			throws JSONException {

		Booking booking = new Booking();
		BeanUtils.copyProperties(bookingsDto, booking);

		Optional<Passenger> optional = passengerRepository.findById(bookingsDto.getPassengerId());
		Passenger passenger = new Passenger();
		if (optional.isPresent()) {
			passenger = optional.get();
		}
		if (passenger != null) {

			Optional<Flight> option = flightRepository.findById(bookingsDto.getFlightNumber());
			Flight flight = new Flight();
			if (option.isPresent()) {
				flight = option.get();
			}
			if (flight.getAvailableSeats() > bookingsDto.getNumberOfSeats()) {
				flightRepository.save(flight);
			} else {
				throw new NoSeatsAvailableException("no seats available");
			}
			flight.setAvailableSeats(flight.getAvailableSeats() - bookingsDto.getNumberOfSeats());
			flight.setFlightName(flight.getFlightName());
			booking.setPassenger(passenger);
			booking.setFlight(flight);
			booking.setNoOfSeats(bookingsDto.getNumberOfSeats());
			booking.setAmmount(bookingsDto.getNumberOfSeats() * flight.getFare());

			Booking booking1 = bookingRepository.save(booking);

			int ammount = booking1.getAmmount();
			try {
				if (ammount > 0) {

					String uri = "http://ACCOUNT-SERVICE/transfer";

					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);

					JSONObject request = new JSONObject();
					request.put("savingsAccountNumber", passengerAccountNumber);
					request.put("beneficiaryAccountNumber",133);
					request.put("transferAmmount", ammount);

					HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

					restTemplate.postForEntity(uri, entity, String.class);
				}

			} catch (Exception e) {
				throw new InsufficientFundsException("the sufficient amount is not in your account");
			}
			dto.setFlightName(booking1.getFlight().getFlightName());
			dto.setFlightNumber(booking1.getFlight().getFlightNumber());
			dto.setNoOfSeats(booking1.getNoOfSeats());
			dto.setJourneyDate(booking1.getJourneyDate());
			dto.setTicketNumber(booking1.getTicketNumber());

		}
		return booking;

	}
	@Override
	public Booking getTicketById(int id) {
		Optional<Booking> option = bookingRepository.findById(id);
		Booking booking = null;
		if (option.isPresent()) {
			booking = option.get();

		} else {
			throw new TicketNotFoundException("Booking with id: " + id + "  Not found");
		}
		return booking;
	}	
	

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
