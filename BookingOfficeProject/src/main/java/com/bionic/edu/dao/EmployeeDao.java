package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entities.Employee;
import com.bionic.edu.entities.EmployeeStatus;
import com.bionic.edu.entities.Role;



public interface EmployeeDao {
	public void save(Employee employee);
	public void updateRole(int id, Role role);
	public void updateStatus(int id, EmployeeStatus status);
	public Employee findById(int id);
	public List<Employee> findAll();
	public Employee login(String login, String password);


}
