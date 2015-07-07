package com.bionic.edu.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TicketDetails")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="bookingId")
	private Booking booking;
	private String passFirstName;
	private String passLastName;
	private Date passDateOfBirth;
	private String passPassportNo;
	private double price;
	@ManyToOne
	@JoinColumn(name="flightId")
	private TimeTableRow row;

	public Ticket() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public String getPassFirstName() {
		return passFirstName;
	}

	public void setPassFirstName(String passFirstName) {
		this.passFirstName = passFirstName;
	}

	public String getPassLastName() {
		return passLastName;
	}

	public void setPassLastName(String passLastName) {
		this.passLastName = passLastName;
	}

	public Date getPassDateOfBirth() {
		return passDateOfBirth;
	}

	public void setPassDateOfBirth(Date passDateOfBirth) {
		this.passDateOfBirth = passDateOfBirth;
	}

	public String getPassPassportNo() {
		return passPassportNo;
	}

	public void setPassPassportNo(String passPassportNo) {
		this.passPassportNo = passPassportNo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TimeTableRow getRow() {
		return row;
	}

	public void setRow(TimeTableRow row) {
		this.row = row;
	}





}
