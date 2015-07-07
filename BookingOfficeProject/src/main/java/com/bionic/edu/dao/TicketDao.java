package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entities.ReportDate;
import com.bionic.edu.entities.ReportDestination;
import com.bionic.edu.entities.Ticket;
import com.bionic.edu.entities.TotalReport;

public interface TicketDao {
	public void save(Ticket ticket);
	public List<ReportDate> getReportByPeriod(LocalDate start, LocalDate end);
	public List<ReportDestination> getReportByDestinations(LocalDate start, LocalDate end);
	public List<TotalReport> getTotalReport(LocalDate start, LocalDate end);
	public Ticket findById(int id);
}
