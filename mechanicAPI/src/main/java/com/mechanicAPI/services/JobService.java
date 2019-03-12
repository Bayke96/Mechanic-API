package com.mechanicAPI.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.mechanicAPI.models.Job;

@Service
public class JobService {
	
	public static SessionFactory factory = HibernateSession.factory;
	
	// -------------------- LIST ALL JOBS ----------------------- //
	
	public List<Job> getJobs() {
		
		List<Job> resultados = new ArrayList<Job>();
		Session session = factory.openSession();
		Transaction tx = null;
  
		try {
			tx = session.beginTransaction();
			String hql = "FROM Job";
			Query query = session.createQuery(hql);
			resultados = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return resultados;
	}
	
	// -------------------- GET A SPECIFIC JOB ----------------------- //
	
	public Job getJob(int jobID) {
		
		Job job = new Job();
		Session session = factory.openSession();
	    Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         String hql = "FROM Job WHERE id = :id";
	         Query query = session.createQuery(hql);
	         query.setParameter("id", jobID);
	         List resultados = query.list();
	         for (Iterator iterator = resultados.iterator(); iterator.hasNext();){
	            job = (Job) iterator.next(); 
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		return job;
	}
	
	// -------------------- REGISTER A NEW JOB ----------------------- //
	
	public Job createJob(Job job) {
		
		int objectID = 0;
		Session session = factory.openSession();
	    Transaction tx = null;
	    
	      try {

	         tx = session.beginTransaction();
	         session.save(job); 
	         tx.commit();
	         String sql = "SELECT id FROM Jobs Order by id DESC LIMIT 1";
	         NativeQuery query = session.createSQLQuery(sql);
	         objectID = Integer.parseInt(query.getSingleResult().toString());
	         job.setID(objectID);
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return job;
	      
	}
	
	// -------------------- UPDATE AN EXISTING JOB ----------------------- //
	
	public Job updateJob(Integer jobID, Job job) {
		
		Session session = factory.openSession();
	    Transaction tx = null;
	    Job object = new Job();
	      try {
	         tx = session.beginTransaction();
	         object = (Job) session.get(Job.class, jobID); 
	         
	         object.setDate(new Date());
	         object.setCustomerID(job.getCustomerID());
	         object.setDescription(job.getDescription());
	         object.setJobStatus(job.getJobStatus());
	         object.setMechanicID(job.getMechanicID());
	         object.setReview(job.getReview());
	         
			 session.update(object); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return object;
	}
	
	// --------------- METHOD TO DELETE AN EXISTING JOB -------------------- //
	
		public Job deleteUser(Integer jobID) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    Job object = new Job();
		      try {
		         tx = session.beginTransaction();
		         object = (Job) session.get(Job.class, jobID); 
		         String SQL = "DELETE FROM Reviews WHERE id = :id";
		         NativeQuery actualizacion = session.createSQLQuery(SQL);
		         actualizacion.setParameter("id", object.getReview().getId());
		         actualizacion.executeUpdate();
		         session.delete(object); 
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return object;
		}

}
