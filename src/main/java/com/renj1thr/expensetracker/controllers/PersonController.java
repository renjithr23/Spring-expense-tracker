package com.renj1thr.expensetracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  @RequestMapping("/persons")
  public ResponseEntity<List<Person>> getAll() {
    return ResponseEntity.ok(this.personService.getPersons());
  }

  @GetMapping("/person/{id}")
  public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) throws NotFoundException {
    return ResponseEntity.ok(this.personService.getPersonById(id));
  }

  @PostMapping("/person/{id}")
  @RequestMapping(method = RequestMethod.POST, value = "/persons")
  public ResponseEntity<Person> addPerson(@RequestBody Person person) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.addPerson(person));
  }

  @GetMapping("/person/{id}/accounts")
  public ResponseEntity<List<Account>> getAccounts(@PathVariable("id") long id) {
    return ResponseEntity.ok(this.personService.getAccounts(id));
  }

  @DeleteMapping("/person/{id}")
  public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
    Map<String, String> result = new HashMap<>();
    result.put("message", this.personService.deletePerson(id));

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
  }
}
