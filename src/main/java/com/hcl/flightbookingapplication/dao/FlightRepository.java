package com.hcl.flightbookingapplication.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.flightbookingapplication.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
	public List<Flight> findByOriginAndDestinationAndJourneyDate(String origin,String destination,Date date);
}
