package com.bionic.edu.services;

import java.util.Date;
import java.util.List;

import com.bionic.edu.entities.ReportDate;
import com.bionic.edu.entities.ReportDestination;
import com.bionic.edu.entities.Ticket;
import com.bionic.edu.entities.TotalReport;

public interface TicketService {
	public void save(Ticket ticket);
	public List<ReportDate> getReportByPeriod(Date start, Date end);
	public List<ReportDestination> getReportByDestinations(Date start, Date end);
	public List<TotalReport> getTotalReport(Date start, Date end);
	public Ticket findById(int id);
}
