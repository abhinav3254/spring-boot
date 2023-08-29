package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Flight;

@RequestMapping("/flight")
public interface FlightRest {
	
	@PostMapping("/add")
	ResponseEntity<String> addFlight(@RequestBody(required = true)Map<String, String> requestMap);

	@PostMapping("/find")
	ResponseEntity<List<Flight>> findBetweenSourceAndDestination(@RequestBody(required = true)Map<String, String> requestMap);
	
}
