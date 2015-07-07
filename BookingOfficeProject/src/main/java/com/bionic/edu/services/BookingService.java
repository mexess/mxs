package com.bionic.edu.services;

import java.util.List;

import com.bionic.edu.entities.Booking;

public interface BookingService {
	public int convertToFreeStatus();
	public void save(Booking booking);
	public Booking findById(int id);
	public List<Booking> findAllBookedTickets();
	public void changeToSoldStatus(int id);
}
