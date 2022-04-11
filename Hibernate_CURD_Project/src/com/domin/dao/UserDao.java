package com.domin.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.domin.model.User;


import url.HibernateUtil;

public class UserDao 
{
	/**
	 * CURD Operation
	 */
	//insert user
	public void saveUser(User user) 
	{
		Transaction transction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			//start transaction
			transction=session.beginTransaction();
			
			//save the student object
			session.save(user);
			
			//commit transaction
			transction.commit();
		}
		catch (Exception e) {
			if(transction!=null) {
				transction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * Update User
	 */
	public void updateUser(User user)
	{
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			//start transaction
			transaction=session.beginTransaction();
			//save the student object
			session.update(user);
			//commit transaction
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete user
	 */
	
	public void deleteUser(int id)
	{
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			//start transaction
			transaction=session.beginTransaction();
			
			//delete a user Object
			User user=session.get(User.class,id);
			if(user!=null) {
				session.delete(user);
				System.out.println("User is deleted");
			}
			//commit transaction
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	/**
	 * Get user by id(single user)
	 */
	
	public User getUser(int id) 
	{
		Transaction transaction=null;
		User user=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			transaction=session.beginTransaction();
			user=session.get(User.class, id);
			transaction.commit();
		}
		catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Get all Users
	 */
	@SuppressWarnings("unchecked")
	public List<User>getAllUser()
	{
		Transaction transaction=null;
		List<User> listOfUser=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			transaction=session.beginTransaction();
			listOfUser=session.createQuery("from User").getResultList();
			transaction.commit();
		}
		catch (Exception e) 
		{
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;		
	}
}
