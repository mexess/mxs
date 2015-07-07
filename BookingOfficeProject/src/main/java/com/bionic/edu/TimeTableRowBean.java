package com.bionic.edu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Flight;
import com.bionic.edu.entities.FlightStatus;
import com.bionic.edu.entities.TimeTableRow;
import com.bionic.edu.services.FlightService;
import com.bionic.edu.services.TimeTableRowService;


@Named
@Scope("session")
public class TimeTableRowBean {
	private List<TimeTableRow> rows;
	private TimeTableRow row;
	private Date date;
	private TimeTableRow selectedRow;
	private Flight selectedFlight;
	private int oldTickets;
	private String depCity;
	private String destCity;
	private int ticketsWanted;

	@Inject
	private TimeTableRowService timeTableRowService;

	@Inject FlightService flightService;

	public String calculateMinDate() {
		String result = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		return result;
	}


	public TimeTableRowBean() {
		row = new TimeTableRow();
	}

	public List<TimeTableRow> getRows() {
		return rows;
	}

	public void setRows(List<TimeTableRow> rows) {
		this.rows = rows;
	}

	public String removeRow() {



		if (selectedRow.getTickets().size() == 0) {
			timeTableRowService.remove(selectedRow.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully deleted."));
			rows.remove(selectedRow);
			findRowsOnDate();
			return "timetable";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "This row cannot be deleted."));
			return "timetable";
		}

	}


	public void cleanSearchHistory() {
		date = null;
		rows = null;
		depCity = "";
		destCity = "";
		ticketsWanted = 1;
	}

	public String findRowsOnDate() {
		rows = timeTableRowService.findAllOnDate(date);
		System.out.println("HELLO " + rows);
		return "timetable";
	}

	public String findRowsOnDateAndPlace() {
		rows = timeTableRowService.findByDateAndPlace(date, depCity, destCity);
		return "";
	}

	public String findAvailableFlights() {
		rows = timeTableRowService.findAvailableFlights(date, depCity, destCity, ticketsWanted);
		return "";
	}

	public List<String> completeDepText(String query) {
		List<String> depCities = flightService.getDepCities();
		List<String> filterCities = new ArrayList<String>();

		for (int i = 0; i < depCities.size(); i++) {
			String city = depCities.get(i);
			if (city.toLowerCase().startsWith(query.toLowerCase())) {
				filterCities.add(city);
			}
		}

		return filterCities;
	}

	public List<String> completeDestText(String query) {
		List<String> destCities = flightService.getDestCities();
		List<String> filterCities = new ArrayList<String>();

		for (int i = 0; i < destCities.size(); i++) {
			String city = destCities.get(i);
			if (city.toLowerCase().startsWith(query.toLowerCase())) {
				filterCities.add(city);
			}
		}

		return filterCities;
	}

	public TimeTableRow getRow() {
		return row;
	}

	public void setRow(TimeTableRow row) {
		this.row = row;
	}

	public String saveRow() {

		int tickets = row.getAmountOfTickets();
		if (row.getId() == 0) {
			row.setAmountOfAvailableTickets(tickets);
		} else {
			int diff = oldTickets - tickets;
			int available = row.getAmountOfAvailableTickets();
			available -= diff;
			row.setAmountOfAvailableTickets(available);
		}
		timeTableRowService.save(row);
		date = row.getDate();
		findRowsOnDate();
		return "timetable";
	}

	public FlightStatus[] getStatuses() {
		return FlightStatus.values();
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String addRow() {
		row = new TimeTableRow();
		return "newRow";
	}

	public TimeTableRow getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(TimeTableRow selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getDepCity() {
		return depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public int getTicketsWanted() {
		return ticketsWanted;
	}

	public void setTicketsWanted(int ticketsWanted) {
		this.ticketsWanted = ticketsWanted;
	}

	public String editRow(String id) {
		int n = Integer.parseInt(id);
		row = timeTableRowService.findById(n);
		oldTickets = row.getAmountOfTickets();
		return "newRow";

	}

	public long calculateMin() {
		if (row.getId() == 0) {
			return 0;
		}

		int all = row.getAmountOfTickets();

		int available = row.getAmountOfAvailableTickets();

		int diff = all - available;
		return diff;
	}

	public String switchToNewRow() {
		row.setFlight(selectedFlight);
		return "newRow";
	}
}
