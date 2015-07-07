package com.bionic.edu.services;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.TimeTableRowDao;
import com.bionic.edu.entities.FlightStatus;
import com.bionic.edu.entities.TimeTableRow;

@Named
public class TimeTableRowServiceImpl implements TimeTableRowService {

	@Inject
	private TimeTableRowDao timeTableRowDao;

	public TimeTableRow findById(int id) {
		return timeTableRowDao.findById(id);
	}

	@Transactional
	public void save(TimeTableRow row) {
		timeTableRowDao.save(row);
	}

	@Transactional
	public void remove(int id) {
		timeTableRowDao.remove(id);
	}

	@Override
	public List<TimeTableRow> findByDateAndPlace(Date date, String fromCity,
			String toCity) {
		return timeTableRowDao.findByDateAndPlace(
				new java.sql.Date(date.getTime()), fromCity, toCity);
	}

	@Transactional
	public void updateStatus(int id, FlightStatus status) {
		timeTableRowDao.updateStatus(id, status);

	}

	@Override
	public List<TimeTableRow> findAllOnDate(Date date) {
		return timeTableRowDao.findAllOnDate(new java.sql.Date(date.getTime()));
	}

	@Override
	public List<TimeTableRow> findAvailableFlights(Date date, String fromCity,
			String toCity, int ticketsWanted) {
		Time time = Time.valueOf(LocalTime.now());
		Date today = new Date();

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());

		if (sqlDate.compareTo(sqlToday) <= 0) {
			return timeTableRowDao.findAvailableFlightsToday(sqlDate, time,
					fromCity, toCity, ticketsWanted);
		} else {
			return timeTableRowDao.findAvailableFlights(sqlDate, fromCity,
					toCity, ticketsWanted);
		}
	}

}
