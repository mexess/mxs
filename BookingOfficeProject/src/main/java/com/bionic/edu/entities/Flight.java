package com.bionic.edu.entities;

import java.sql.Time;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String flightNumber;
	private String fromCity;
	private String toCity;
	private Time depTime;
	private Time duration;
	private String airway;
	private String aircraft;

	@OneToMany(mappedBy="flight")
	private Collection<TimeTableRow> rows;

	public Flight() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public String getAirway() {
		return airway;
	}

	public void setAirway(String airway) {
		this.airway = airway;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public Collection<TimeTableRow> getRows() {
		return rows;
	}

	public void setRows(Collection<TimeTableRow> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightNumber=" + flightNumber
				+ ", fromCity=" + fromCity + ", toCity=" + toCity
				+ ", depTime=" + depTime + ", duration=" + duration
				+ ", airway=" + airway + ", aircraft=" + aircraft + "]";
	}

}
