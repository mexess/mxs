package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Customer customer) {
		em.persist(customer);
	}

	@Override
	public Customer findById(int id) {
	return em.find(Customer.class, id);
	}



}
