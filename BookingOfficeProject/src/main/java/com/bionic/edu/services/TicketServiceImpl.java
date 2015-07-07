package com.bionic.edu.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.TicketDao;

import com.bionic.edu.entities.ReportDate;
import com.bionic.edu.entities.ReportDestination;
import com.bionic.edu.entities.Ticket;

import com.bionic.edu.entities.TotalReport;

@Named
public class TicketServiceImpl implements TicketService {
	@Inject
	private TicketDao ticketDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Ticket ticket) {
		ticketDao.save(ticket);
	}

	@Override
	public List<ReportDate> getReportByPeriod(Date start, Date end) {

		LocalDate startDate = new java.sql.Date(start.getTime()).toLocalDate();
		LocalDate endDate = new java.sql.Date(end.getTime()).toLocalDate();

		return ticketDao.getReportByPeriod(startDate, endDate);
	}

	@Override
	public List<TotalReport> getTotalReport(Date start, Date end) {
		LocalDate startDate = new java.sql.Date(start.getTime()).toLocalDate();
		LocalDate endDate = new java.sql.Date(end.getTime()).toLocalDate();

		return ticketDao.getTotalReport(startDate, endDate);
	}

	@Override
	public List<ReportDestination> getReportByDestinations(Date start, Date end) {

		LocalDate startDate = new java.sql.Date(start.getTime()).toLocalDate();
		LocalDate endDate = new java.sql.Date(end.getTime()).toLocalDate();

		return ticketDao.getReportByDestinations(startDate, endDate);
	}

	@Override
	public Ticket findById(int id) {
		return ticketDao.findById(id);
	}

}
