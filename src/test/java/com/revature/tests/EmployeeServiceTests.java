package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests 
{
	private EmployeeDAO mockdao;
	private EmployeeService eserv;
	
	@Before
	public void setup()
	{
		mockdao = mock(EmployeeDAO.class);
		eserv = new EmployeeService(mockdao);
	}
	
	@After
	public void teardown()
	{
		mockdao = null;
		eserv = null;
	}
	
	
	//Happy path scenario
	@Test
	public void testConfirmLogin_success()
	{
		//create fake db of employee obj
		Employee e1 = new Employee(3,"Scott","Lang","Antman","Bugs");
		Employee e2 = new Employee(23,"Clint","Barton","Hawkeye","Arrows");
		//add to a list
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		//when mockdao findall is called it returns dummy list
		when(mockdao.findAll()).thenReturn(emps);
		//use an assert equals on the eserv.confirmlogin that we return the right user
		assertEquals(e2, eserv.confirmLogin("Hawkeye", "Arrows"));
	
	}
	
	@Test
	public void testFailConfirmLogin()
	{
		//create fake db of employee obj
		Employee e1 = new Employee(3,"Scott","Lang","Antman","Bugs");
		Employee e2 = new Employee(23,"Clint","Barton","Hawkeye","Arrows");
		//add to a list
		List<Employee> emps = new ArrayList<Employee>();
		emps.add(e1);
		emps.add(e2);
		//when mockdao findall is called it returns dummy list
		when(mockdao.findAll()).thenReturn(emps);
		
		assertNull(eserv.confirmLogin("Hawkeye", "Arrow"));
	}
}
