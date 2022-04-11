package com.domin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domin.dao.UserDao;
import com.domin.model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User u = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = request.getServletPath();
		
		switch(path) 
		{
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertUser(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateUser(request, response);
			break;
		case "/delete":
			deleteUser(request, response);
			break;
			default:
				listUser(request, response);
				break;
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		UserDao.deleteUser(id);
		
		try 
		{
			response.sendRedirect("list");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		u = new User(id, name, email, country);
		
		UserDao.updateUser(u);
		
		try 
		{
			response.sendRedirect("list");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			u = UserDao.selectUserById(id);
			request.setAttribute("user", u);
			RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			ArrayList<User> alUsers = UserDao.selectAllUsers();
			request.setAttribute("listUser", alUsers);
			RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
	{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		u = new User(name, email, country);
		
		UserDao.insertUser(u);
		
		try 
		{
			response.sendRedirect("list");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
