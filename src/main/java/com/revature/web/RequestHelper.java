package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper 
{
	private static Logger log = Logger.getLogger(RequestHelper.class);
	
	private static EmployeeService empService = new EmployeeService(new EmployeeDAO());
	
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		//extract parameters from servlet request
		BufferedReader reader = request.getReader();
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		
		while(line != null)
		{
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		String[] valuesByAmp = body.split("&");
		
		List<String> values = new ArrayList<String>();
		
		for(String pair: valuesByAmp)
		{
			values.add(pair.substring(pair.indexOf('=')+1));
		}
		String username = values.get(0);
		String password = values.get(1);
		
		log.info("User attempted to login with username " + username);
		
		Employee e = empService.confirmLogin(username,password);
		
		if(e != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println(om.writeValueAsString(e));
		}
		else
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found");
			response.setStatus(204);
		}
		//call service layer 
		//return response
	}
	
	public static void processEmployees(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html");
		
		List<Employee> allEmployees = empService.findAll();
		String jsonString = om.writeValueAsString(allEmployees);
		
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		
	}
	
	public static void processError(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		request.getRequestDispatcher("error.html").forward(request, response);
		//extract parameters from servlet request
	}
}
