package com.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.FlightDAO;
import com.jwt.JwtFilter;
import com.pojo.Flight;
import com.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDAO flightDAO;
	
	@Autowired
	private JwtFilter filter;
	
	@Override
	public ResponseEntity<String> addFlights(Map<String, String> requestMap) {
		try {
			if (filter.isAdmin()) {
				Flight flight = flightConfig(requestMap);
				if (Objects.isNull(flight)) {
					String messageBuild1 = "{"
							+ "\n message: Date ISSUE MAY BE CHECK DATE IS IN ISO OR NOT"
									+ "\n}";
					return new ResponseEntity<String>(messageBuild1,HttpStatus.OK);
				} else {
				flightDAO.save(flight);
				String messageBuild1 = "{"
						+ "\n message: Flight Added Successfully"
								+ "\n}";
				return new ResponseEntity<String>(messageBuild1,HttpStatus.OK);
				}
			} else {
				String messageBuild1 = "{"
						+ "\n message: UNAUTHORIZED ACCESS"
								+ "\n}";
				return new ResponseEntity<String>(messageBuild1,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.somethingWentWrong("something went wrong");
	}
	
	private Flight flightConfig(Map<String, String> map) {
		
		try {
			Flight flight = new Flight();
			
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime localDateTime1 = LocalDateTime.parse(map.get("departureTime"), formatter);
			LocalDateTime localDateTime2 = LocalDateTime.parse(map.get("arrivalTime"), formatter);

			
			flight.setFlightNumber(map.get("flightNumber"));
			flight.setAirline(map.get("airline"));
			flight.setDepartureAirport(map.get("departureAirport"));
			flight.setArrivalAirport(map.get("arrivalAirport"));
			flight.setArrivalTime(localDateTime2);
			flight.setDepartureTime(localDateTime1);
			flight.setAircraftType(map.get("aircraftType"));
			flight.setSeatCapacity(Integer.parseInt(map.get("seatCapacity")));
			flight.setTicketPrice(Double.parseDouble(map.get("ticketPrice")));
			flight.setStatus(map.get("status"));
			
			return flight;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<List<Flight>> findBetweenSourceAndDestination(Map<String, String> requestMap) {
		try {
			List<Flight> listFlight = flightDAO.findBetweenSourceAndDestination(requestMap.get("src"),requestMap.get("des"));
			return new ResponseEntity<List<Flight>>(listFlight,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Flight>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 

}
