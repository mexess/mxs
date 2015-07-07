package com.bionic.edu;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;


import com.bionic.edu.entities.Flight;
import com.bionic.edu.services.FlightService;

@Named
@Scope("session")
public class FlightBean {

	private List<Flight> flights;
	private Flight flight;
	private String dest;
	private Flight selectedFlight;

	@Inject
	private FlightService flightService;

	public FlightBean() {
	}

	public String removeFlight() {


		if (selectedFlight.getRows().size() == 0) {
			flightService.remove(selectedFlight.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully deleted."));
			flights.remove(selectedFlight);
			dest = selectedFlight.getToCity();
			findFlights();
			return "flights";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "This flight cannot be deleted."));
			return "flights";
		}

	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public String findFlights() {
		flights = flightService.findByDest(dest);
		return "flights";
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}



	public String addFlight() {
		flight = new Flight();
		return "newFlight";
	}

	public String saveFlight() {

		flightService.save(flight);
		dest = flight.getToCity();
		findFlights();
		return "flights";
	}

	public String editFlight(String id) {
		int n = Integer.parseInt(id);
		flight = flightService.findById(n);
		return "newFlight";

	}


}
