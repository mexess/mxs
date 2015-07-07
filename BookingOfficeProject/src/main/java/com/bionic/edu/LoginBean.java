package com.bionic.edu;

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
public class LoginBean {
	private String username;
	private String password;
	private Employee employee;

	@Inject
	private EmployeeService employeeService;

	public LoginBean() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "login";
	}

	public String login() {
		employee = employeeService.login(username, password);
		if (employee == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect username/password",
							null));
			return "";
		}

		if (employee.getStatus() == EmployeeStatus.DEACTIVATED.ordinal()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Your account is deactivated!",
							null));
			employee = null;
			return "";
		}

		if (employee.getRole() == Role.ADMINISTRATOR.ordinal()) {
			return "timetable";
		}

		if (employee.getRole() == Role.ACCOUNTANT.ordinal()) {
			return "accountant";
		}

		if (employee.getRole() == Role.BUSINESS_ANALYST.ordinal()) {
			return "analytic";
		}

		if (employee.getRole() == Role.SECURITY_OFFICER.ordinal()) {
			return "employees";
		}

		return "";
	}

	public String banner() {

		if (employee == null) {
			return "index";
		}

		if (employee.getRole() == Role.ADMINISTRATOR.ordinal()) {
			return "timetable";
		}

		if (employee.getRole() == Role.ACCOUNTANT.ordinal()) {
			return "accountant";
		}

		if (employee.getRole() == Role.BUSINESS_ANALYST.ordinal()) {
			return "analytic";
		}

		if (employee.getRole() == Role.SECURITY_OFFICER.ordinal()) {
			return "employees";
		}

		return "";
	}

	public void backToLogin() {
		try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isLoggedIn() {
		return employee != null;
	}

	public String welcome() {
		return "Welcome, " + employee.getFirstName() + " "
				+ employee.getLastName() + "!";
	}

	public boolean isAdmin() {
		if (employee != null) {
			return employee.getRole() == Role.ADMINISTRATOR.ordinal();
		} else {
			return false;
		}
	}

	public boolean isAnalytic() {
		if (employee != null) {
			return employee.getRole() == Role.BUSINESS_ANALYST.ordinal();
		} else {
			return false;
		}
	}

	public boolean isAccountant() {
		if (employee != null) {
			return employee.getRole() == Role.ACCOUNTANT
					.ordinal();
		} else {
			return false;
		}
	}

	public boolean isSecurity() {
		if (employee != null) {
			return employee.getRole() == Role.SECURITY_OFFICER.ordinal();
		} else {
			return false;
		}
	}

}
