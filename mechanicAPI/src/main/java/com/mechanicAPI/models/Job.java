package com.mechanicAPI.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Jobs")
public class Job {

	@Id
	@NotNull
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "job_date")
	private Date date = new Date();
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "mechanic_ID")
	private User mechanicID;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "customer_ID")
	private User customerID;
	
	@Size(min = 3, max = 255)
	@NotNull
	@Column(name = "job_description")
	private String description;
		
	@OneToOne
	@JoinColumn(name = "review_ID")
	private Review review;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "job_status")
	private JobStatus jobStatus;
	
	// ---------------------------------- GETTERS ------------------------------------ //
	
	public int getId() {
		return this.id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public User getMechanicID() {
		return this.mechanicID;
	}
	
	public User getCustomerID() {
		return this.customerID;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Review getReview() {
		return this.review;
	}
	
	@Enumerated(EnumType.STRING)
	public JobStatus getJobStatus() {
		return this.jobStatus;
	}
	
	// ---------------------------------- SETTERS ------------------------------------ //
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setMechanicID(User mechanic) {
		this.mechanicID = mechanic;
	}
	
	public void setCustomerID(User customer) {
		this.customerID = customer;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setReview(Review review) {
		this.review = review;
	}
	
	@Enumerated(EnumType.STRING)
	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	
}
