package com.renj1thr.expensetracker.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository personRepository;
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Set<Person> getPerson() {
		
		Set<Person> personSet = new HashSet<Person>();
		personRepository.findAll().iterator().forEachRemaining(personSet::add);
		return personSet;
	}
	
	@Override
	public List<Person> getPersons(){
		List<Person> personList = new ArrayList<Person>();
		personRepository.findAll().iterator().forEachRemaining(personList::add);
		return personList;
	}
	
	@Override
	public Person getPersonById(long id) {
		Optional<Person> personOptional =  personRepository.findById(id);
		
		if(!personOptional.isPresent()) {
			throw new RuntimeException("Person Not Found!");
		}
		
		return personOptional.get();
	}
	
	@Override
	public Person addPerson(Person personFromFE) {
		Person person = personFromFE;
		person.setAccounts(new HashSet<Account>());
		
		person = personRepository.save(person);
		
		return person;
	}
	
	
	
	
}
