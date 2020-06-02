package com.hcl.flightbookingapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.flightbookingapplication.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

}
