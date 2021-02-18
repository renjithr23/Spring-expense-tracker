package com.renj1thr.expensetracker.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.AccountRepository;
import com.renj1thr.expensetracker.repositories.PersonRepository;
import com.renj1thr.expensetracker.services.AccountServiceImpl;
import com.renj1thr.expensetracker.services.ExpenseServiceImpl;
import com.renj1thr.expensetracker.services.IncomeServiceImpl;
import com.renj1thr.expensetracker.services.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

  PersonServiceImpl personService;
  AccountServiceImpl accountService;
  IncomeServiceImpl incomeService;
  ExpenseServiceImpl expenseService;

  @Mock PersonController personController;

  @Mock PersonRepository personRepository;

  @Mock AccountRepository accountRepository;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    AccountServiceImpl accountService =
        new AccountServiceImpl(accountRepository, expenseService, incomeService);
    personService = new PersonServiceImpl(personRepository, accountService);

    personController = new PersonController(personService);
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }

  @Test
  public void testGetPerson() throws Exception {
    //		given
    Person person = Person.builder().id(1L).build();
    Optional<Person> personOptional = Optional.of(person);

    //		when
    when(personRepository.findById(anyLong())).thenReturn(personOptional);

    //		then
    mockMvc
        .perform(get("/person/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("id")));
  }

  @Test
  public void testGetAccounts() throws Exception {
    //		given
    Person person = Person.builder().id(1L).build();

    Account account1 = Account.builder().id(1L).name("account1").type("type1").balance(0).build();

    Account account2 = Account.builder().id(2L).name("account2").type("type2").balance(0).build();

    Set<Account> accountSet = new HashSet<Account>();
    accountSet.add(account2);
    accountSet.add(account1);
    person.setAccounts(accountSet);

    Optional<Person> personOptional = Optional.of(person);

    //		when
    when(personRepository.findById(1L)).thenReturn(personOptional);

    //		then
    mockMvc
        .perform(get("/person/1/accounts"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("id")));

    verify(personRepository, times(1)).findById(anyLong());
    verify(personRepository, never()).findAll();

    mockMvc.perform(get("/person/3/accounts")).andExpect(status().isNotFound());
  }
}
