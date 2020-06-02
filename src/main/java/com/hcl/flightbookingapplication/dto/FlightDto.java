package com.hcl.flightbookingapplication.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class FlightDto {
	private String origin;
	private String destination;
	@Temporal(TemporalType.DATE)
	private Date journeyDate;

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

}
