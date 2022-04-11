package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FirstServlet implements Servlet {
	
	ServletConfig conf;
	@Override
	public void destroy() {
		System.out.println("Destroy method.....");
	}

	@Override
	public ServletConfig getServletConfig() {
		return conf;
	}

	@Override
	public String getServletInfo() {
		return "Kick Nain";
	}

	@Override
	public void init(ServletConfig conf) throws ServletException {
		System.out.println("This is a init method...");

	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("this is a service Method...");
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<h1>This is a first Servlet page...</h1>");
		out.println("<h1>This is a first Servlet page Time And Date :: "+new Date().toString()+"</h1>");

	}

}
