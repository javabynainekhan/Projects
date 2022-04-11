package com.domin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.domin.model.User;

public class UserDao 
{
	private final static String url = "jdbc:mysql://localhost:3306/usermanagement";
	private final static String username = "root";
	private final static String password = "mohammed";
	
	private final static String insertUser = "insert into user(name, email, country) values (?,?,?)";
	private final static String selectUserById = "select * from user where id = ?";
	private final static String selectAllUsers = "select * from user";
	private final static String updateUser = "update user set name = ?, email = ?, country = ? where id = ?";
	private final static String deleteUser = "delete from user where id = ?";
	
	private static Connection cn = null;
	private static Statement s = null;
	private static PreparedStatement ps = null;
	
	public static Connection getDbConnection() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, username, password);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cn;
	}
	
	public static void insertUser(User u) 
	{
		try 
		{
			cn = getDbConnection();
			ps = cn.prepareStatement(insertUser);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getCountry());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				ps.close();
				cn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<User> selectAllUsers() 
	{
		ArrayList<User> alUser = new ArrayList<User>();
		try 
		{
			cn = getDbConnection();
			s = cn.createStatement();
			ResultSet rs = s.executeQuery(selectAllUsers);
			
			while(rs.next()) 
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				/* 1st way:-
				/*
				 * User u = new User(id, name, email, country); 
				 * alUser.add(u);
				 * -----------------------------
				2nd way:- */
				alUser.add(new User(id, name, email, country));
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alUser;
	}
	
	public static User selectUserById(int id) 
	{
		User u = null;
		try 
		{
			cn = getDbConnection();
			ps = cn.prepareStatement(selectUserById);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				u = new User(id, name, email, country);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return u;
	}
	
	public static void updateUser(User u)
	{
		try 
		{
			cn = getDbConnection();
			ps = cn.prepareStatement(updateUser);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getCountry());
			ps.setInt(4, u.getId());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				ps.close();
				cn.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static void deleteUser(int id) 
	{
		try 
		{
			cn = getDbConnection();
			ps = cn.prepareStatement(deleteUser);
			ps.setInt(1, id);
			ps.executeUpdate();		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				ps.close();
				cn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
