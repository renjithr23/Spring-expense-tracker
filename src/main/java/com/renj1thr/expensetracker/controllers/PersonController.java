package com.renj1thr.expensetracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.exceptions.NotFoundException;
import com.renj1thr.expensetracker.services.PersonService;

@RestController
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	@RequestMapping("/getPersons")
	public List<Person> getAll(){
		return this.personService.getPersons();
	}
	
	@GetMapping
	@RequestMapping(method = RequestMethod.GET, value = "/getPerson/{id}")
	public Person getPersonById(@PathVariable("id") long id) throws NotFoundException {
		return this.personService.getPersonById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addPerson")
	public Person addPerson(@RequestBody Person person) {
		return this.personService.addPerson(person);
	}
	
	@GetMapping
	@RequestMapping("/person/{id}/accounts")
	public List<Account> getAccounts(@PathVariable("id") long id){
		return this.personService.getAccounts(id);
	}
	
}
