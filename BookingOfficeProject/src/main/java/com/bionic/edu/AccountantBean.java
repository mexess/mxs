package com.bionic.edu;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Booking;
import com.bionic.edu.entities.TicketStatus;
import com.bionic.edu.services.BookingService;

@Named
@Scope("session")
public class AccountantBean {

	private List<Booking> bookings;
	private Booking booking;

	@Inject
	BookingService bookingService;

	public AccountantBean() {

	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public String changeToSoldStatus(String id) {
		int n = Integer.parseInt(id);
		bookingService.changeToSoldStatus(n);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfully changed to sold status."));
		findAllBookedTickets();
		return "accountant";
	}

	public String findAllBookedTickets() {
		bookings = bookingService.findAllBookedTickets();
		return "accountant";
	}

	public TicketStatus[] getStatuses() {
		return TicketStatus.values();
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}
