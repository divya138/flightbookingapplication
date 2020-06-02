package com.hcl.flightbookingapplication.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightNumber;
	private String origin;
	private String destination;
	@Temporal(TemporalType.DATE)
	private Date journeyDate;
	private int toatalSeats;
	private int availableSeats;

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flight(String origin, String destination, Date journeyDate, int toatalSeats, int availableSeats,
			String flightName, int fare, Set<com.hcl.flightbookingapplication.model.Booking> booking) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.journeyDate = journeyDate;
		this.toatalSeats = toatalSeats;
		this.availableSeats = availableSeats;
		this.flightName = flightName;
		this.fare = fare;
		this.booking = booking;
	}

	private String flightName;
	private int fare;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flight")
	private Set<Booking> booking;

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getToatalSeats() {
		return toatalSeats;
	}

	public void setToatalSeats(int toatalSeats) {
		this.toatalSeats = toatalSeats;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}

}
