package com.bionic.edu.dao;

import com.bionic.edu.entities.Customer;

public interface CustomerDao {
	public void save(Customer customer);
	public Customer findById(int id);
}
