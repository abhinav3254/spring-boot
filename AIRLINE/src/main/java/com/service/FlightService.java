package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Flight;

public interface FlightService {
	
	ResponseEntity<String> addFlights(Map<String, String> requestMap);
	
	ResponseEntity<List<Flight>> findBetweenSourceAndDestination(Map<String, String> requestMap);


}
