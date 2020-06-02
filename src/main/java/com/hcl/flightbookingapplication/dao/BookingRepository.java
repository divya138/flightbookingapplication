package com.hcl.flightbookingapplication.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.flightbookingapplication.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	Optional<Booking> findByTicketNumber(int id);

}
