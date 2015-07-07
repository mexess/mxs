package com.bionic.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entities.Employee;
import com.bionic.edu.entities.EmployeeStatus;
import com.bionic.edu.entities.Role;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@PersistenceContext
	private EntityManager em;

	public void save(Employee employee) {
		if (employee.getId() == 0) {
		em.persist(employee);
		} else {
			em.merge(employee);
		}
	}

	public void updateRole(int id, Role role) {
		Employee e = em.find(Employee.class, id);

		if (e != null) {
			e.setRole(role.ordinal());
		}
	}

	public void updateStatus(int id, EmployeeStatus status) {
		Employee e = em.find(Employee.class, id);

		if (e != null) {
			e.setStatus(status.ordinal());
		}
	}

	public Employee findById(int id) {
		return em.find(Employee.class, id);
	}

	public List<Employee> findAll() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e",
				Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee login(String login, String password) {
		TypedQuery<Employee> query = em
				.createQuery(
						"SELECT e FROM Employee e WHERE e.login = :login AND e.password = :password",
						Employee.class);

		query.setParameter("login", login);
		query.setParameter("password", password);


		try {
		Employee employee = query.getSingleResult();
		return employee;
		} catch (Exception e) {
			return null;
		}


	}


}
