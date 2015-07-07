package com.bionic.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.Flight;

@Repository
public class FlightDaoImpl implements FlightDao {
	@PersistenceContext
	private EntityManager em;

	public Flight findById(int id) {
		return em.find(Flight.class, id);
	}

	public void save(Flight flight) {
		if (flight.getId() == 0) {
		em.persist(flight);
		} else {
			em.merge(flight);
		}
	}

	public void remove(int id) {
		em.remove(em.find(Flight.class, id));
	}

	@Override
	public List<Flight> findByDest(String dest) {

		String txt = "SELECT f FROM Flight f WHERE f.toCity = ?1";
		TypedQuery<Flight> query = em.createQuery(txt, Flight.class);
		query.setParameter(1, dest);
		return query.getResultList();
	}

	@Override
	public List<String> getDepCities() {
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT f.fromCity FROM Flight f", String.class);
		return query.getResultList();
	}

	@Override
	public List<String> getDestCities() {
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT f.toCity FROM Flight f", String.class);
		return query.getResultList();
	}

}
