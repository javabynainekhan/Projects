package com.domin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class MyServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public MyServlet() 
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();		
		 response.setContentType("text/html");
		/*  pw.println("<h1>Hello World");
		 * pw.print("<h2 color=red>Always\n");
		 * pw.append("<h1 bgcolor=red>Java is awesome</h1>");
		 */
		String email=request.getParameter("Email");
		String pwd=request.getParameter("Password");
		pw.println("<h1> Login Details :");
		pw.println("<h2> EmailId :"+email);
		pw.println("<h2> PassWord :"+pwd);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		doGet(request, response);
	}

}
