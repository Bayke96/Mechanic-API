package com.mechanicAPI.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Reviews")
public class Review {
	
	@Id
	@NotNull
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "review_date")
	@NotNull
	private Date date = new Date();
	
	@OneToOne
	@NotNull
	@JoinColumn(name = "job_FK", unique = true)
	private Job jobFK;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "customer_FK")
	private User customerFK;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "mechanic_FK")
	private User mechanicFK;
	
	@NotNull
	@Column(name = "review_description")
	@Size(min = 3, max = 255)
	private String description;
	
	@Min(0)
	@Max(5)
	@NotNull
	@Column(name = "quality_score")
	private double score;
	
	// ---------------------------------- GETTERS ------------------------------------ //
	
	public int getId() {
		return this.id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Job getJobFK() {
		return this.jobFK;
	}
	
	public User getCustomerFK() {
		return this.customerFK;
	}
	
	public User getMechanicFK() {
		return this.mechanicFK;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Double getScore() {
		return this.score;
	}
	
	// ---------------------------------- SETTERS ------------------------------------ //
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setJobFK(Job job) {
		this.jobFK = job;
	}
	
	public void setCustomerFK(User customerID) {
		this.customerFK = customerID;
	}
	
	public void setMechanicFK(User mechanicID) {
		this.mechanicFK = mechanicID;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}

}
