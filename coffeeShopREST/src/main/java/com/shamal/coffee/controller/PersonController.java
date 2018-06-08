package com.shamal.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shamal.coffee.domain.Person;
import com.shamal.coffee.service.PersonService;


@RestController
@RequestMapping("/person-api")
public class PersonController {
	
	@Autowired
	PersonService personService; 
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getPeople(){
		return new ResponseEntity<List<Person>>(personService.getAllPeople(),HttpStatus.OK);
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable("id") long id){
		return new ResponseEntity<Person>(personService.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		return new ResponseEntity<Person>(personService.savePerson(person),HttpStatus.CREATED);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		return new ResponseEntity<Person>(personService.savePerson(person),HttpStatus.OK);
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable("id") long id){
		
		HttpHeaders header= new HttpHeaders();
		
		Person person =personService.findById(id);
		if(person==null)
			return new ResponseEntity<Void>(header,HttpStatus.NOT_FOUND);
		
		personService.removePerson(person);
		return new ResponseEntity<Void>(header,HttpStatus.OK);
		
	}

}
