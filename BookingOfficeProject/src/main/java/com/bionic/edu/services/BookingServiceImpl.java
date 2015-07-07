package com.bionic.edu.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.BookingDao;
import com.bionic.edu.dao.TimeTableRowDao;
import com.bionic.edu.entities.Booking;
import com.bionic.edu.entities.Ticket;
import com.bionic.edu.entities.TicketStatus;
import com.bionic.edu.entities.TimeTableRow;

@Named
public class BookingServiceImpl implements BookingService {
	@Inject
	private BookingDao bookingDao;
	@Inject
	private TimeTableRowDao timeTableRowDao;

	@Transactional
	public int convertToFreeStatus() {
		List<Booking> bookings = bookingDao.getOverdueBookings();
		for (Booking booking : bookings) {
			bookingDao.updateStatus(booking.getId(), TicketStatus.FREE);
			for (Ticket ticket : booking.getTickets()) {
				TimeTableRow row = ticket.getRow();
				int amount = row.getAmountOfAvailableTickets();
				row.setAmountOfAvailableTickets(++amount);
				timeTableRowDao.save(row);
			}
		}
		return bookings.size();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Booking booking) {
		bookingDao.save(booking);
	}

	@Override
	public Booking findById(int id) {
		return bookingDao.findById(id);
	}

	@Override
	public List<Booking> findAllBookedTickets() {
		return bookingDao.findAllBookedTickets();
	}

	@Transactional
	public void changeToSoldStatus(int id) {
		Booking booking = bookingDao.findById(id);

		if (booking != null) {
			bookingDao.updateStatus(id, TicketStatus.SOLD);
		}
	}

}
