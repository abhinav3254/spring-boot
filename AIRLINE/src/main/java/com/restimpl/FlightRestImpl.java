package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Flight;
import com.rest.FlightRest;
import com.service.FlightService;

@RestController
public class FlightRestImpl implements FlightRest {
	
	@Autowired
	private FlightService flightService;

	@Override
	public ResponseEntity<String> addFlight(Map<String, String> requestMap) {
		try {
			return flightService.addFlights(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.somethingWentWrong("something went wrong");
	}

	@Override
	public ResponseEntity<List<Flight>> findBetweenSourceAndDestination(Map<String, String> requestMap) {
		try {
			return flightService.findBetweenSourceAndDestination(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Flight>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
