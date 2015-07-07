package com.bionic.edu.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.bionic.edu.entities.FlightStatus;
import com.bionic.edu.entities.TimeTableRow;

public interface TimeTableRowDao {
	public TimeTableRow findById(int id);
	public void save(TimeTableRow row);
	public void remove(int id);
	public void updateStatus(int id, FlightStatus status);
	public List<TimeTableRow> findByDateAndPlace(Date date, String fromCity, String toCity);
	public List<TimeTableRow> findAllOnDate(Date date);
	public List<TimeTableRow> findAvailableFlights(Date date, String fromCity, String toCity, int ticketsWanted);
	public List<TimeTableRow> findAvailableFlightsToday(Date date, Time time, String fromCity, String toCity, int ticketsWanted);
}
