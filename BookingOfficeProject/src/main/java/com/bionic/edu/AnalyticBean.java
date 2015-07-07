package com.bionic.edu;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.ReportDate;
import com.bionic.edu.entities.ReportDestination;
import com.bionic.edu.entities.TotalReport;
import com.bionic.edu.services.TicketService;

@Named
@Scope("session")
public class AnalyticBean {

	@Inject
	TicketService ticketService;
	private List<TotalReport> total;
	private List<ReportDate> reportDates;
	private List<ReportDestination> reportDestinations;
	private Date start;
	private Date end;
	private double maxDate;
	private double maxDest;

	private BarChartModel dateModel;
	private BarChartModel destModel;


	public BarChartModel getDateModel() {
		return dateModel;
	}

	public BarChartModel getDestModel() {
		return destModel;
	}




	public String totalReport() {
		if (start.compareTo(end) > 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You've selected wrong period."));
			return "";
		}
		total = ticketService.getTotalReport(start, end);
		System.out.println("Hello From " + total);
		return "analytic";
	}

	public String findReportDates() {
		reportDates = ticketService.getReportByPeriod(start, end);
		createDateModel();
		return "reportdates";
	}

	public String findReportDestinations() {
		reportDestinations = ticketService.getReportByDestinations(start, end);
		createDestModel();
		return "reportdestination";
	}

	public AnalyticBean() {

	}

	public List<ReportDate> getReportDates() {
		return reportDates;
	}

	public void setReportDates(List<ReportDate> reportDates) {
		this.reportDates = reportDates;
	}

	public List<ReportDestination> getReportDestinations() {
		return reportDestinations;
	}

	public void setReportDestinations(List<ReportDestination> reportDestinations) {
		this.reportDestinations = reportDestinations;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public List<TotalReport> getTotal() {
		return total;
	}

	public void setTotal(List<TotalReport> total) {
		this.total = total;
	}

	private BarChartModel initDestModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries tickets = new ChartSeries();
        tickets.setLabel("Sold tickets");

        for (int i = 0; i < reportDestinations.size(); i++) {

        	ReportDestination reportDestination = reportDestinations.get(i);
        	if (reportDestination.getSum() > maxDest) {
        		maxDest = reportDestination.getSum() + 1000;
        	}

        	tickets.set(reportDestination.getDestination(), reportDestination.getSum());
        }

        model.addSeries(tickets);

        return model;
	}

	private BarChartModel initDateModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries tickets = new ChartSeries();
        tickets.setLabel("Sold tickets");

        for (int i = 0; i < reportDates.size(); i++) {

        	ReportDate reportDate = reportDates.get(i);
        	if (reportDate.getSum() > maxDate) {
        		maxDate = reportDate.getSum() + 1000;
        	}

        	tickets.set(reportDate.getDate(), reportDate.getSum());
        }

        model.addSeries(tickets);

        return model;
	}

	private void createDateModel() {
		dateModel = initDateModel();
		dateModel.setTitle("Sum of sold tickets by date");
		dateModel.setAnimate(true);
		dateModel.setLegendPosition("ne");
		Axis yAxis = dateModel.getAxis(AxisType.Y);
		yAxis = dateModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxDate);

	}

	private void createDestModel() {
		destModel = initDestModel();
		destModel.setTitle("Sum of sold tickets by destination");
		destModel.setAnimate(true);
		destModel.setLegendPosition("ne");
		Axis yAxis = destModel.getAxis(AxisType.Y);
		yAxis = destModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxDest);
	}
}
