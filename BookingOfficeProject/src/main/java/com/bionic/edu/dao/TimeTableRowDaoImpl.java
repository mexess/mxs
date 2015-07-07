package com.bionic.edu.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.FlightStatus;
import com.bionic.edu.entities.TimeTableRow;

@Repository
public class TimeTableRowDaoImpl implements TimeTableRowDao {

	@PersistenceContext
	private EntityManager em;

	public TimeTableRow findById(int id) {
		return em.find(TimeTableRow.class, id);
	}

	public void save(TimeTableRow row) {

		if (row.getId() == 0) {
			em.persist(row);
		} else {
			em.merge(row);
		}
	}

	public void remove(int id) {
		TimeTableRow row = em.find(TimeTableRow.class, id);

		if (row != null) {

			em.remove(row);
		}
	}

	@Override
	public List<TimeTableRow> findByDateAndPlace(Date date, String fromCity,
			String toCity) {
		String txt = "SELECT t FROM TimeTableRow t WHERE ";
		txt += "(t.date = ?1 AND t.flight.fromCity = ?2 AND t.flight.toCity = ?3 AND t.status = 1)";
		TypedQuery<TimeTableRow> query = em
				.createQuery(txt, TimeTableRow.class);
		query.setParameter(1, date);
		query.setParameter(2, fromCity);
		query.setParameter(3, toCity);

		return query.getResultList();
	}

	@Override
	public void updateStatus(int id, FlightStatus status) {
		TimeTableRow row = em.find(TimeTableRow.class, id);

		if (row != null) {
			row.setStatus(status);
		}

	}

	@Override
	public List<TimeTableRow> findAllOnDate(Date date) {
		TypedQuery<TimeTableRow> query = em.createQuery(
				"SELECT t FROM TimeTableRow t WHERE t.date = ?1",
				TimeTableRow.class);

		query.setParameter(1, date);

		return query.getResultList();
	}

	@Override
	public List<TimeTableRow> findAvailableFlights(Date date,
			String fromCity, String toCity, int ticketsWanted) {

		String txt = "SELECT t FROM TimeTableRow t WHERE ";
		txt += "(t.date = ?1  AND t.flight.fromCity = ?2 AND t.flight.toCity = ?3 AND t.amountOfAvailableTickets >= ?4 AND t.status = 1)";

		TypedQuery<TimeTableRow> query = em
				.createQuery(txt, TimeTableRow.class);
		query.setParameter(1, date);
		query.setParameter(2, fromCity);
		query.setParameter(3, toCity);
		query.setParameter(4, ticketsWanted);

		return query.getResultList();

	}

	@Override
	public List<TimeTableRow> findAvailableFlightsToday(Date date, Time time,
			String fromCity, String toCity, int ticketsWanted) {
		String txt = "SELECT t FROM TimeTableRow t WHERE ";
		txt += "(t.date = ?1 AND t.flight.depTime > ?2 AND t.flight.fromCity = ?3 AND t.flight.toCity = ?4 AND t.amountOfAvailableTickets >= ?5) AND t.status = 1";

		TypedQuery<TimeTableRow> query = em
				.createQuery(txt, TimeTableRow.class);
		query.setParameter(1, date);
		query.setParameter(2, time);
		query.setParameter(3, fromCity);
		query.setParameter(4, toCity);
		query.setParameter(5, ticketsWanted);

		return query.getResultList();
	}

}
