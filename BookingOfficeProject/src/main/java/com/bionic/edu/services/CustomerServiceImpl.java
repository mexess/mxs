package com.bionic.edu.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.CustomerDao;
import com.bionic.edu.entities.Customer;

@Named
public class CustomerServiceImpl implements CustomerService {
	@Inject
	private CustomerDao customerDao;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void save(Customer customer) {
		customerDao.save(customer);
		
	}

	@Override
	public Customer findById(int id) {
		return customerDao.findById(id);
	}

}
