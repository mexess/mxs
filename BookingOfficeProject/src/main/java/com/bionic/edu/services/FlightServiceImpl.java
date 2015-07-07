package com.bionic.edu.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.FlightDao;
import com.bionic.edu.entities.Flight;

@Named
public class FlightServiceImpl implements FlightService {
	@Inject
	private FlightDao flightDao;

	public Flight findById(int id) {
		return flightDao.findById(id);
	}

	@Transactional
	public void save(Flight flight) {
		flightDao.save(flight);
	}

	@Transactional
	public void remove(int id) {

		Flight flight = findById(id);

		if (flight != null) {
			flightDao.remove(id);
		}
	}

	@Override
	public List<Flight> findByDest(String dest) {
		return flightDao.findByDest(dest);
	}

	@Override
	public List<String> getDepCities() {
		return flightDao.getDepCities();
	}

	@Override
	public List<String> getDestCities() {
		return flightDao.getDestCities();
	}

}
