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
import com.mechanicAPI.models.Review;

@Service
public class ReviewService {
	
	public static SessionFactory factory = HibernateSession.factory;
	
	// -------------------- LIST ALL REVIEWS ----------------------- //
	
		public List<Review> getReviews() {
			
			List<Review> resultados = new ArrayList<Review>();
			Session session = factory.openSession();
			Transaction tx = null;
	  
			try {
				tx = session.beginTransaction();
				String hql = "FROM Review";
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
		
		// -------------------- GET A SPECIFIC REVIEW ----------------------- //
		
		public Review getReview(int reviewID) {
			
			Review review = new Review();
			Session session = factory.openSession();
		    Transaction tx = null;
		      
		      try {
		         tx = session.beginTransaction();
		         String hql = "FROM Review WHERE id = :id";
		         Query query = session.createQuery(hql);
		         query.setParameter("id", reviewID);
		         List resultados = query.list();
		         for (Iterator iterator = resultados.iterator(); iterator.hasNext();){
		            review = (Review) iterator.next(); 
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
			return review;
		}
		
		// -------------------- CREATE A NEW REVIEW ----------------------- //
		
		public Review createReview(Review review) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    
		      try {

		         tx = session.beginTransaction();
		         session.save(review); 
		         tx.commit();
		         String sql = "SELECT id FROM Reviews Order by id DESC LIMIT 1";
		         NativeQuery query = session.createSQLQuery(sql);
		         review.setId(Integer.parseInt(query.getSingleResult().toString()));
		         
		         sql = "UPDATE Jobs SET review_ID = :reviewID WHERE id = :id";
		         query = session.createSQLQuery(sql);
		         query.setParameter("reviewID", review.getId());
		         query.setParameter("id", review.getJobFK().getId());
		         query.executeUpdate();
		         
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return review;
		      
		}

		// -------------------- UPDATE AN EXISTING REVIEW ----------------------- //
		
		public Review updateReview(Integer reviewID, Review review) {
			
			Session session = factory.openSession();
		    Transaction tx = null;
		    Review object = new Review();
		    
		      try {
		         tx = session.beginTransaction();
		         object = (Review) session.get(Review.class, reviewID); 
		         
		         object.setDate(new Date());
		         object.setCustomerFK(review.getCustomerFK());
		         object.setDescription(review.getDescription());
		         object.setJobFK(review.getJobFK());
		         object.setMechanicFK(review.getMechanicFK());
		         object.setScore(review.getScore());
		         
				 session.update(object); 
				 
				 String sql = "UPDATE Jobs SET review_ID = :reviewID WHERE id = :id";
		         NativeQuery query = session.createSQLQuery(sql);
		         query.setParameter("reviewID", object.getId());
		         query.setParameter("id", review.getJobFK().getId());
		         query.executeUpdate();
				 
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return object;
		}

		// --------------- METHOD TO DELETE AN EXISTING REVIEW -------------------- //
		
			public Review deleteReview(Integer reviewID) {
				
				Session session = factory.openSession();
			    Transaction tx = null;
			    Review object = new Review();
			      try {
			         tx = session.beginTransaction();
			         object = (Review) session.get(Review.class, reviewID); 
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
