package com.mechanicAPI.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechanicAPI.models.Review;
import com.mechanicAPI.services.ReviewService;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	
	ReviewService reviewService = new ReviewService();
	
	// ------------------- REST API CALL TO GET ALL REVIEWS ---------------------------- //
	
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getReviews(HttpServletResponse response) {
			
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		    response.setHeader("Pragma","no-cache");
		    response.setHeader("Version","Mechanic API V-2.0");
		    response.setDateHeader("Expires", 0);
		    
			return new ResponseEntity<>( reviewService.getReviews(), HttpStatus.OK);
		}
		
	// ------------------- REST API CALL TO GET A REVIEW ---------------------------- //
		
		@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getReview(HttpServletResponse response, @PathVariable("id") int id) {
			
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
			response.setHeader("Pragma","no-cache");
			response.setHeader("Version","Mechanic API V-2.0");
			response.setDateHeader("Expires", 0);
		    
			return new ResponseEntity<>( reviewService.getReview(id), HttpStatus.OK);
	}
		
	// ------------------- REST API CALL TO CREATE A REVIEW ---------------------------- //
		
		@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> createReview(Review review, HttpServletRequest request, HttpServletResponse response) {
			
			Review newReview = reviewService.createReview(review);
			
			response.addHeader("Job Created", request.getServerName() + ":" +  request.getServerPort() + "/reviews/" + newReview.getId());
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		    response.setHeader("Pragma","no-cache");
		    response.setHeader("Version","Mechanic API V-2.0");
		    response.setDateHeader("Expires", 0);
			
			return new ResponseEntity<>(newReview, HttpStatus.CREATED);
		} 
		
	// ------------------- REST API CALL TO UPDATE A REVIEW ---------------------------- //
		
		@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> updateReview(@PathVariable("id") int id, Review review, HttpServletResponse response) {
					
			Review updatedReview = reviewService.updateReview(id, review);

			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
			response.setHeader("Pragma","no-cache");
			response.setHeader("Version","Mechanic API V-2.0");
			response.setDateHeader("Expires", 0);
					
			return new ResponseEntity<>(updatedReview, HttpStatus.CREATED);
		} 
		
		// ------------------- REST API CALL TO DELETE A REVIEW ---------------------------- //
		
			@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Object> deleteReview(@PathVariable("id") int id, HttpServletResponse response) {
							
				Review deletedReview = reviewService.deleteReview(id);
							
				response.addHeader("Date", new Date().toString());
				response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
				response.setHeader("Pragma","no-cache");
				response.setHeader("Version","Mechanic API V-2.0");
				response.setDateHeader("Expires", 0);
							
				return new ResponseEntity<>(deletedReview, HttpStatus.CREATED);
			} 


}
