package com.bionic.edu.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.ReportDate;
import com.bionic.edu.entities.ReportDestination;
import com.bionic.edu.entities.Ticket;
import com.bionic.edu.entities.TicketStatus;
import com.bionic.edu.entities.TotalReport;

@Repository
public class TicketDaoImpl implements TicketDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Ticket ticket) {
		if (ticket.getId() == 0) {
		em.persist(ticket);
		} else {
			em.merge(ticket);
		}
	}

	@Override
	public List<ReportDate> getReportByPeriod(LocalDate start, LocalDate end) {

		String txt = "SELECT new com.bionic.edu.entities.ReportDate(t.row.date, SUM(t.price), COUNT(t.id)) ";
		txt += " FROM Ticket t";
		txt += " WHERE t.booking.ticketStatus = " + TicketStatus.SOLD.ordinal();
		txt += " AND t.row.date BETWEEN ?1 AND ?2";
		txt += " GROUP BY t.row.date";

		TypedQuery<ReportDate> query = em.createQuery(txt, ReportDate.class);

		query.setParameter(1, Date.valueOf(start));
		query.setParameter(2, Date.valueOf(end));

		return query.getResultList();
	}

	@Override
	public List<ReportDestination> getReportByDestinations(LocalDate start, LocalDate end) {
		String txt = "SELECT new com.bionic.edu.entities.ReportDestination(t.row.flight.toCity, SUM(t.price), COUNT(t.id)) ";
		txt += " FROM Ticket t";
		txt += " WHERE t.booking.ticketStatus = " + TicketStatus.SOLD.ordinal();
		txt += " AND t.row.date BETWEEN ?1 AND ?2";
		txt += " GROUP BY t.row.flight.toCity";

		TypedQuery<ReportDestination> query = em.createQuery(txt, ReportDestination.class);

		query.setParameter(1, Date.valueOf(start));
		query.setParameter(2, Date.valueOf(end));

		return query.getResultList();
	}

	@Override
	public Ticket findById(int id) {
		return em.find(Ticket.class, id);
	}

	@Override
	public List<TotalReport> getTotalReport(LocalDate start, LocalDate end) {
		String txt = "SELECT new com.bionic.edu.entities.TotalReport(SUM(t.price), COUNT(t.id)) ";
		txt += " FROM Ticket t";
		txt += " WHERE t.booking.ticketStatus = " + TicketStatus.SOLD.ordinal();
		txt += " AND t.row.date BETWEEN ?1 AND ?2";

		TypedQuery<TotalReport> query = em.createQuery(txt, TotalReport.class);

		query.setParameter(1, Date.valueOf(start));
		query.setParameter(2, Date.valueOf(end));

		return query.getResultList();
	}


}
