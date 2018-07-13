package com.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.DAO.UserDaoService_Static;
import com.rest.webservices.restfulwebservices.bean.User;
import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.sun.jndi.toolkit.url.Uri;

@RestController
public class UserResourceController_Static {
	
	@Autowired
	private UserDaoService_Static service;

	//retrieve all users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//retrieve user
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		//HATEOAS
		Resource<User> resource = new Resource <User> (user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		ControllerLinkBuilder linkToThis = linkTo(methodOn(this.getClass()).retrieveUser(id));
		
		resource.add(linkTo.withRel("for all users"));
		resource.add(linkToThis.withSelfRel());
		
		return resource;
	}
	
	//add user
	@PostMapping("/users")
	public ResponseEntity addUser(@Valid @RequestBody User user) {
		User addedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(addedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//Delete User by id
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("id-" + id);
		}
	}
	
}
