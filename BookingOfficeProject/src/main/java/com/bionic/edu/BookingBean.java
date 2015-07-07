package com.bionic.edu;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Booking;
import com.bionic.edu.entities.Customer;
import com.bionic.edu.entities.Ticket;
import com.bionic.edu.entities.TicketStatus;
import com.bionic.edu.entities.TimeTableRow;
import com.bionic.edu.services.BookingService;
import com.bionic.edu.services.CustomerService;
import com.bionic.edu.services.TicketService;
import com.bionic.edu.services.TimeTableRowService;

@Named
@Scope("session")
public class BookingBean {

	private TimeTableRow bookedRow;
	private List<Ticket> completedTickets;
	private List<Booking> completedBookings;
	private Ticket ticket;
	private Booking booking;
	private Customer customer;
	private double totalPrice;
	private List<Ticket> tickets;
	@Inject
	BookingService bookingService;

	@Inject
	TicketService ticketService;

	@Inject
	CustomerService customerService;

	@Inject
	TimeTableRowService timeTableRowService;

	public BookingBean() {
		completedTickets = new ArrayList<Ticket>();
	}

	public void backToMain() {
		try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertToFreeStatus() {
		int n = bookingService.convertToFreeStatus();
		String msg = "";
		if (n == 1) {
			msg += n + " overdue booking was";
		} else {
			msg += n + " overdue bookings were";
		}

		msg += " converted in a free status";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
		return "";
	}

	public String saveBooking() {



		for (Ticket ticket : completedTickets) {
			TimeTableRow row = ticket.getRow();
			int amount = row.getAmountOfAvailableTickets();
			row.setAmountOfAvailableTickets(--amount);
			timeTableRowService.save(row);
		}

		booking.setTickets(completedTickets);
		booking.setTicketStatus(TicketStatus.BOOKED);
		booking.setTotalPrice(calcTotalPrice());
		booking.setCustomer(customer);
		booking.setDate(Timestamp.valueOf(LocalDateTime.now()));

		completedBookings = new ArrayList<Booking>();
		completedBookings.add(booking);

		customer.setBookings(completedBookings);

		customerService.save(customer);

		completedTickets = new ArrayList<Ticket>();
		booking = null;

		return "booked";
	}

	public String editTicket() {
		return "editTicket";
	}

	public double calcTotalPrice() {

		totalPrice = 0;
		for (Ticket ticket : completedTickets) {
			totalPrice += ticket.getPrice();
		}

		return totalPrice;
	}

	public String goToCart() {
		if (tickets != null)
			completedTickets.addAll(tickets);
		return "cart";
	}

	public TimeTableRow getBookedRow() {
		return bookedRow;
	}

	public void setBookedRow(TimeTableRow bookedRow) {
		this.bookedRow = bookedRow;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public List<Ticket> getCompletedTickets() {
		return completedTickets;
	}

	public void setCompletedTickets(List<Ticket> completedTickets) {
		this.completedTickets = completedTickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Booking> getCompletedBookings() {
		return completedBookings;
	}

	public void setCompletedBookings(List<Booking> completedBookings) {
		this.completedBookings = completedBookings;
	}

	public String book(String id, int number) {
		int n = Integer.parseInt(id);
		bookedRow = timeTableRowService.findById(n);
		if (booking == null) {
			booking = new Booking();
		}
		tickets = new ArrayList<Ticket>();
		for (int i = 0; i < number; i++) {
			Ticket ticket = new Ticket();
			ticket.setRow(bookedRow);
			ticket.setPrice(bookedRow.getPrice());
			ticket.setBooking(booking);
			tickets.add(ticket);
		}

		return "passdetails";
	}

	public String removeTicket() {
		completedTickets.remove(ticket);
		System.out.println("NIGGER");
		return "cart";
	}

	public String getCustomerInfo() {

		if (completedTickets.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"No tickets for booking."));
			return "cart";
		}

		if (customer == null) {
			customer = new Customer();
		}
		return "customerinfo";
	}

}
