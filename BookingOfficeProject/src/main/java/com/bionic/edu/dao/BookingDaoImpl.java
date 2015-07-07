package com.bionic.edu.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.Booking;
import com.bionic.edu.entities.TicketStatus;

@Repository
public class BookingDaoImpl implements BookingDao {
	@PersistenceContext
	private EntityManager em;


	public List<Booking > getOverdueBookings() {
		Timestamp checkDate = Timestamp.valueOf(LocalDateTime.now().minusDays(3));
		String txt = "SELECT b FROM Booking b WHERE b.date < :check AND b.ticketStatus = :status";
		TypedQuery<Booking> query = em.createQuery(txt, Booking.class);
		query.setParameter("check", checkDate);
		query.setParameter("status", TicketStatus.BOOKED.ordinal());

		return query.getResultList();

	}

	@Override
	public void save(Booking booking) {
		if (booking.getId() == 0) {
		em.persist(booking);
		} else {
			em.merge(booking);
		}
	}

	@Override
	public Booking findById(int id) {
		return em.find(Booking.class, id);
	}


	@Override
	public void updateStatus(int id, TicketStatus status) {
		Booking booking = em.find(Booking.class, id);

		if (booking != null) {
			booking.setTicketStatus(status);
		}

	}

	@Override
	public List<Booking> findAllBookedTickets() {
		TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.ticketStatus = " + TicketStatus.BOOKED.ordinal(), Booking.class);
		return query.getResultList();
	}

}
