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

import com.mechanicAPI.models.Job;
import com.mechanicAPI.services.JobService;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/jobs")
public class JobController {
	
	JobService jobService = new JobService();
	
	// ------------------- REST API CALL TO GET ALL JOBS ---------------------------- //
	
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getJObs(HttpServletResponse response) {
			
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		    response.setHeader("Pragma","no-cache");
		    response.setHeader("Version","Mechanic API V-2.0");
		    response.setDateHeader("Expires", 0);
		    
			return new ResponseEntity<>( jobService.getJobs(), HttpStatus.OK);
		}
		
	// ------------------- REST API CALL TO GET A JOB ---------------------------- //
		
		@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getJob(HttpServletResponse response, @PathVariable("id") int id) {
			
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
			response.setHeader("Pragma","no-cache");
			response.setHeader("Version","Mechanic API V-2.0");
			response.setDateHeader("Expires", 0);
		    
			return new ResponseEntity<>( jobService.getJob(id), HttpStatus.OK);
	}
		
	// ------------------- REST API CALL TO CREATE A JOB ---------------------------- //
		
		@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> createJob(Job job, HttpServletRequest request, HttpServletResponse response) {
			
			Job newJob = jobService.createJob(job);
			
			response.addHeader("Job Created", request.getServerName() + ":" +  request.getServerPort() + "/jobs/" + newJob.getId());
			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		    response.setHeader("Pragma","no-cache");
		    response.setHeader("Version","Mechanic API V-2.0");
		    response.setDateHeader("Expires", 0);
			
			return new ResponseEntity<>(newJob, HttpStatus.CREATED);
		} 
		
	// ------------------- REST API CALL TO UPDATE A JOB ---------------------------- //
		
		@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> updateJob(@PathVariable("id") int id, Job job, HttpServletResponse response) {
					
			Job updatedJob = jobService.updateJob(id, job);

			response.addHeader("Date", new Date().toString());
			response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
			response.setHeader("Pragma","no-cache");
			response.setHeader("Version","Mechanic API V-2.0");
			response.setDateHeader("Expires", 0);
					
			return new ResponseEntity<>(updatedJob, HttpStatus.CREATED);
		} 
		
		// ------------------- REST API CALL TO DELETE A JOB ---------------------------- //
		
			@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Object> deleteJob(@PathVariable("id") int id, HttpServletResponse response) {
							
				Job deletedJob = jobService.deleteUser(id);
							
				response.addHeader("Date", new Date().toString());
				response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
				response.setHeader("Pragma","no-cache");
				response.setHeader("Version","Mechanic API V-2.0");
				response.setDateHeader("Expires", 0);
							
				return new ResponseEntity<>(deletedJob, HttpStatus.CREATED);
			} 

}
