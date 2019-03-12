package com.mechanicAPI.controllers;

import java.security.Principal;
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

import com.mechanicAPI.models.User;
import com.mechanicAPI.services.UserService;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/users")
public class UserController {
	
	UserService userService = new UserService();
	
	// ------------------- REST API CALL TO GET ALL USERS ---------------------------- //
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers(HttpServletResponse response) {
		
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
	    
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	// ------------------- REST API CALL TO GET AN USER ---------------------------- //
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUser(HttpServletResponse response, @PathVariable("id") int id) {
		
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
	    
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}
	
	// ------------------- REST API CALL TO VERIFY AN USER'S PASSWORD ---------------------------- //
	
	@PostMapping(value = "/verifyP/{pass}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> verifyPassword(@PathVariable("pass") String pass, HttpServletResponse response,
			Principal principal) {
		
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
		
		boolean result = userService.verifyPassword(principal.getName(), pass.trim());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// ------------------- REST API CALL TO LOGIN AN USER ---------------------------- //
	
	// ------------------- REST API CALL TO CREATE AN USER ---------------------------- //
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(User user, HttpServletRequest request, HttpServletResponse response) {
		
		User newUser = userService.createUser(user);
				
		response.addHeader("User Created", request.getServerName() + ":" +  request.getServerPort() + "/users/" + newUser.getId());
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
		
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	} 
	
	// ------------------- REST API CALL TO UPDATE AN EXISTING USER ---------------------------- //
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUser(HttpServletResponse response, @PathVariable("id") int id, User user) { 
		
		response.addHeader("User Update", "Successful");
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
		
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}
	
	// ------------------- REST API CALL TO DELETE AN EXISTING USER ---------------------------- //
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteUser(HttpServletResponse response, @PathVariable("id") int id) { 
		
		response.addHeader("User Delete", "Successful");
		response.addHeader("Date", new Date().toString());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setHeader("Version","Mechanic API V-2.0");
	    response.setDateHeader("Expires", 0);
		
		return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
	}

}
