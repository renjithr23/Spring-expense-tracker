package com.renj1thr.expensetracker.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.exceptions.NotFoundException;
import com.renj1thr.expensetracker.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final AccountService accountService;

  public PersonServiceImpl(PersonRepository personRepository, AccountService accountService) {
    this.personRepository = personRepository;
    this.accountService = accountService;
  }

  @Override
  public Set<Person> getPerson() {

    Set<Person> personSet = new HashSet<Person>();
    personRepository.findAll().iterator().forEachRemaining(personSet::add);
    return personSet;
  }

  @Override
  public List<Person> getPersons() {
    List<Person> personList = new ArrayList<Person>();
    personRepository.findAll().iterator().forEachRemaining(personList::add);
    return personList;
  }

  @Override
  public Person getPersonById(long id) throws NotFoundException {
    Optional<Person> personOptional = personRepository.findById(id);

    if (!personOptional.isPresent()) {
      throw new NotFoundException("Person with ID " + id + " not found");
    }

    log.info("Person with ID " + id + " returned");
    return personOptional.get();
  }

  @Override
  public Person addPerson(Person personFromFE) {
    Person person = personFromFE;
    person.setAccounts(new HashSet<Account>());

    person = personRepository.save(person);

    log.info("Person with Id " + person.getId() + " added");

    return person;
  }

  @Override
  public List<Account> getAccounts(long id) {
    Person person = this.getPersonById(id);

    List<Account> accountList = new ArrayList<Account>();

    person.getAccounts().iterator().forEachRemaining(accountList::add);

    log.info("Accounts for person " + id + " fetched");

    return accountList;
  }

  @Override
  public Account addAccount(Account account, long id) {
    Person person = this.getPersonById(id);

    account = accountService.addAccount(account, person);

    log.info("Accounts " + account.getId() + "added for person " + id);

    return account;
  }
}
