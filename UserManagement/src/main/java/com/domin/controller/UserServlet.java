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
       
   
    public UserServlet() {
        super();
     }
    User u=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		switch(path) {
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			insertUser(request,response);
			break;
		case "/update":
			updateUser(request,response);
			break;
		case "/delete":
			deleteUser(request,response);
			break;
		case "/edit":
			showEditForm(request,response);
			break;
		default:
			listUser(request,response);
			break;
		}
		
	}

	
	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			ArrayList<User> alUsers=new ArrayList<User>();
			request.setAttribute("user", alUsers);
			RequestDispatcher rd=request.getRequestDispatcher("user-list.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
	         e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id= Integer.parseInt(request.getParameter("id"));
			User u=UserDao.selectUser(id);
			request.setAttribute("user", u);
			RequestDispatcher rd=request.getRequestDispatcher("user-form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		
		int id=Integer.parseInt(request.getParameter("id"));
		UserDao.deleteUser(id);
		try {
			response.sendRedirect("list");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void updateUser(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String country=request.getParameter("country");
		User u=new User(id,name,country,email);
		User u1=new User(password);
		UserDao.updateUser(u,u1);
		try {
			response.sendRedirect("list");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void insertUser(HttpServletRequest request, HttpServletResponse response) {
		int id = 101;
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String country=request.getParameter("country");
		u=new User(id,name,email,country);
		User u1=new User(password);
		UserDao.insertUser(u, u1);
		try {
			response.sendRedirect("list");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			RequestDispatcher rd=request.getRequestDispatcher("user-form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
