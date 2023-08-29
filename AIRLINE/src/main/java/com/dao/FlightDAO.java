package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Integer> {
	
	@Query(nativeQuery = true,value = "\n"
			+ "select * from flight where arrival_airport like %:src% and departure_airport like %:des%")
	List<Flight> findBetweenSourceAndDestination(String src,String des);

}
