package com.bionic.edu.entities;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "TimeTable")
public class TimeTableRow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "flightId")
	private Flight flight;
	private Date date;
	private int amountOfTickets;
	private int amountOfAvailableTickets;
	private double price;
	private int status;

	@OneToMany(mappedBy = "row", cascade = CascadeType.PERSIST)
	private Collection<Ticket> tickets;

	public TimeTableRow() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmountOfTickets() {
		return amountOfTickets;
	}

	public void setAmountOfTickets(int amountOfTickets) {

		if (amountOfTickets < 0) {
			return;
		}
		this.amountOfTickets = amountOfTickets;
	}

	public int getAmountOfAvailableTickets() {
		return amountOfAvailableTickets;
	}

	public void setAmountOfAvailableTickets(int amountOfAvailableTickets) {

		if (amountOfAvailableTickets < 0) {
			return;
		}

		this.amountOfAvailableTickets = amountOfAvailableTickets;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {

		if (price < 0) {
			return;
		}

		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (status < 0 || status >= FlightStatus.values().length) {
			return;
		}
		this.status = status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status.ordinal();
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Flight Number: " + flight.getFlightNumber() + "\nFlight: from city: " + flight.getFromCity()
				+ " \nto city: " + flight.getToCity() + ",\ndate:" + date
				+ ", \namountOfTickets:" + amountOfTickets
				+ ", \namountOfAvailableTickets:" + amountOfAvailableTickets
				+ ", \nprice:" + price + ", \nstatus:"
				+ FlightStatus.values()[status] + "\n";
	}

}
