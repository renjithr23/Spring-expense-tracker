package com.renj1thr.expensetracker.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.AccountRepository;
import com.renj1thr.expensetracker.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  PersonServiceImpl personService;
  IncomeServiceImpl incomeService;
  ExpenseServiceImpl expenseService;

  @Mock PersonRepository personRepository;

  @Mock AccountRepository accountRepository;

  @BeforeEach
  public void setUp() throws Exception {
    AccountServiceImpl accountService =
        new AccountServiceImpl(accountRepository, expenseService, incomeService);
    personService = new PersonServiceImpl(personRepository, accountService);
  }

  @Test
  public void getPersonById() {
    Person person = Person.builder().id(1L).build();
    person.setId(1L);
    Optional<Person> personOptional = Optional.of(person);

    when(personRepository.findById(anyLong())).thenReturn(personOptional);

    Person retunedPerson = personService.getPersonById(1L);
    assertNotNull("Null Person Returned", retunedPerson);
    verify(personRepository, times(1)).findById(anyLong());
    verify(personRepository, never()).findAll();
  }

  @Test
  public void getPersons() {

    List<Person> persons = new ArrayList<Person>();
    Person person1 = Person.builder().id(1L).build();
    Person person2 = Person.builder().id(2L).build();
    persons.add(person1);
    persons.add(person2);

    when(personRepository.findAll()).thenReturn(persons);

    List<Person> returnedPersons = personService.getPersons();
    assertEquals(returnedPersons.size(), 2);
    verify(personRepository, times(1)).findAll();
  }
}
