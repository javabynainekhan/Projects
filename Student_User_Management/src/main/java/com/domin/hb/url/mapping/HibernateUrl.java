package com.domin.hb.url.mapping;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.domin.model.Student;
/**
 * Java Hibernate mapping file
 * or Configuration.cfg.xml file
 * @author julkhar
 *
 */

public class HibernateUrl
{
	private static SessionFactory sf;
	public static SessionFactory getSessionFactory()
	{
		if(sf==null)
		{
			try {
				Configuration cfg=new Configuration();
							
				//Hibernate settings environment configuration.cfg.xml properties
				
				Properties settings=new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "mohammed");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "create-drop");
				
				cfg.setProperties(settings);
				cfg.addAnnotatedClass(Student.class);
				
				ServiceRegistry sr= new StandardServiceRegistryBuilder()
						.applySettings(cfg.getProperties()).build();
				System.out.println("Hibernate Java Config ServiceRegistry created");
				sf=cfg.buildSessionFactory(sr);
				return sf;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sf;
	}
}
