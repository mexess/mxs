package com.bionic.edu.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.EmployeeDao;
import com.bionic.edu.entities.Employee;
import com.bionic.edu.entities.EmployeeStatus;
import com.bionic.edu.entities.Role;

@Named
public class EmployeeServiceImpl implements EmployeeService {
	@Inject
	private EmployeeDao employeeDao;

	@Transactional
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Transactional
	public void updateRole(int id, Role role) {
		employeeDao.updateRole(id, role);
	}

	@Transactional
	public void updateStatus(int id, EmployeeStatus status) {
		employeeDao.updateStatus(id, status);
	}


	public Employee findById(int id) {
		return employeeDao.findById(id);
	}


	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public Employee login(String login, String password) {
		return employeeDao.login(login, password);
	}



}
