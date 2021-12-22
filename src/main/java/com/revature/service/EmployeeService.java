package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;

public class EmployeeService 
{
	private EmployeeDAO eDAO;
	
	public EmployeeService(EmployeeDAO eDAO)
	{
		super();
		this.eDAO = eDAO;
	}
	
	public Employee login(String username, String password)
	{
		Optional<Employee> possibleEmp = eDAO.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();

		return (possibleEmp.isPresent() ? possibleEmp.get(): null);
	}
	
	public Employee confirmLogin(String username,String password)
	{
		return null;
	}
	
	public List<Employee> findAll()
	{
		return eDAO.findAll();
	}
	
	
	public int insert(Employee e)
	{
		return eDAO.insert(e);
	}
	
	public boolean update(Employee e)
	{
		return eDAO.update(e);
	}
	
	public boolean delete(Employee e)
	{
		return eDAO.delete(e);
	}
}
