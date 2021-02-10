package com.renj1thr.expensetracker.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.services.PersonService;

@RestController
public class IndexController {

  private final PersonService personService;

  public IndexController(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping({"", "/", "/index"})
  public Set<Person> getAll() {
    return this.personService.getPerson();
  }
}
