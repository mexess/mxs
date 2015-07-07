package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entities.Booking;
import com.bionic.edu.entities.TicketStatus;

public interface BookingDao {
	public List<Booking> getOverdueBookings();
	public void save(Booking booking);
	public Booking findById(int id);
	public void updateStatus(int id, TicketStatus status);
	public List<Booking> findAllBookedTickets();
}
