package com.bionic.edu.services;

import com.bionic.edu.entities.Customer;

public interface CustomerService {
	public void save(Customer customer);
	public Customer findById(int id);
}
