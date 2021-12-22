package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		final String URI = request.getRequestURI().replace("/FrontControllerDemo/", "");
		switch(URI)
		{
			case "login":
				//call login method and pass the request and response obj
				RequestHelper.processLogin(request, response);
				break;
			case "error":
				//process error types
				break;
			case "":
				break;
			default:
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
