package com.renj1thr.expensetracker.services;

import java.util.List;
import java.util.Set;

import com.renj1thr.expensetracker.domains.Person;

public interface PersonService {
	Set<Person> getPerson();
	List<Person> getPersons();
	Person getPersonById(long id);
	Person addPerson(Person person);
}
