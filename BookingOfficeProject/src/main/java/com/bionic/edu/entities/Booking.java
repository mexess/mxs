package com.bionic.edu.entities;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	private Timestamp date;
	private int ticketStatus;
	private double totalPrice;

	@OneToMany(mappedBy="booking", cascade = CascadeType.PERSIST)
	private Collection<Ticket> tickets;

	public Booking() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(int ticketStatus) {

		if (ticketStatus < 0 || ticketStatus >= TicketStatus.values().length) {
			return;
		}
		this.ticketStatus = ticketStatus;
	}

	public void setTicketStatus(TicketStatus status) {
		this.ticketStatus = status.ordinal();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {

		if (totalPrice < 0) {
			return;
		}

		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}



}
