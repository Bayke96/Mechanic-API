package com.mechanicAPI.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mechanicAPI.models.Job;
import com.mechanicAPI.models.Review;
import com.mechanicAPI.models.Roles;
import com.mechanicAPI.models.User;

public class HibernateSession {
	
	public static SessionFactory factory = configurarSession();
	
	public static SessionFactory configurarSession() {
		
		SessionFactory fact;
				
		try {
	         fact = new Configuration().
	                   configure().
	                   addAnnotatedClass(User.class).
	                   addAnnotatedClass(Roles.class).
	                   addAnnotatedClass(Job.class).
	                   addAnnotatedClass(Review.class).
	                   buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		return fact;
	}
}
