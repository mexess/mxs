package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entities.Flight;


public interface FlightDao {
	public Flight findById(int id);
	public void save(Flight flight);
	public void remove(int id);
	public List<Flight> findByDest(String dest);
	public List<String> getDepCities();
	public List<String> getDestCities();
}
