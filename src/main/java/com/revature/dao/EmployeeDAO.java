package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAO 
{
	public int insert(Employee e)
	{
		//grab session object
		Session ses = HibernateUtil.getSession();
		
		//begin transaction
		Transaction tx = ses.beginTransaction();
		
		//capture pk
		int pk = (int)ses.save(e);
		
		//commit tx
		tx.commit();
		
		return pk;
	}
	
	public List<Employee> findAll()
	{
		Session ses = HibernateUtil.getSession();
		List<Employee> empList = ses.createQuery("from Employee", Employee.class).list();
		return empList;
	}
	
	public boolean update(Employee e)
	{
		return false;
	}
	
	public boolean delete(Employee e)
	{
		return false;
	}
}
