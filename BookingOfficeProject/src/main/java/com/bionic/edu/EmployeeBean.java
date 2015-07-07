package com.bionic.edu;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entities.Employee;
import com.bionic.edu.entities.EmployeeStatus;
import com.bionic.edu.entities.Role;
import com.bionic.edu.services.EmployeeService;

@Named
@Scope("session")
public class EmployeeBean {

	private List<Employee> employees;
	private Employee employee;
	private Employee selectedEmployee;

	@Inject
	private EmployeeService employeeService;

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public EmployeeBean() {

		selectedEmployee = new Employee();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String addEmployee() {
		employee = new Employee();
		return "newEmployee";
	}

	public String findEmployees() {
		employees = employeeService.findAll();
		return "employees";
	}

	public EmployeeStatus[] getStatuses() {
		return EmployeeStatus.values();
	}

	public Role[] getRoles() {
		return Role.values();
	}

	public String saveSelected() {
		employeeService.save(selectedEmployee);
		return "employees";
	}

	public String cancelSelected() {
		findEmployees();
		return "employees";
	}

	public String saveEmployee() {
		try {
		employeeService.save(employee);
		findEmployees();
		return "employees";
		} catch (Exception e) {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "This login is already used"));

			return "newEmployee";
		}
	}

	public String editEmployee(String id) {
		int n = Integer.parseInt(id);
		employee = employeeService.findById(n);
		return "newEmployee";

	}

}
