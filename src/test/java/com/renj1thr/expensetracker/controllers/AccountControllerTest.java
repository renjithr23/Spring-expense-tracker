package com.renj1thr.expensetracker.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.repositories.AccountRepository;
import com.renj1thr.expensetracker.repositories.PersonRepository;
import com.renj1thr.expensetracker.services.AccountServiceImpl;
import com.renj1thr.expensetracker.services.ExpenseServiceImpl;
import com.renj1thr.expensetracker.services.IncomeServiceImpl;
import com.renj1thr.expensetracker.services.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	
	PersonServiceImpl personService;
	AccountServiceImpl accountService;
	IncomeServiceImpl incomeService;
	ExpenseServiceImpl expenseService;
	
	@Mock
	PersonRepository personRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	AccountController accountController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception{
		accountService = new AccountServiceImpl(accountRepository, expenseService, incomeService);
		personService = new PersonServiceImpl(personRepository, accountService);
        
		accountController = new AccountController(personService, accountService);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
	@Test
	public void testGetExpenses() throws Exception{
//		given 
		Account account = Account.builder()
				.id(1L)
				.build();
		Expense expense1 = new Expense();
		expense1.setId(1L);
		expense1.setAmount(200);
		Expense expense2 = new Expense();
		expense2.setId(1L);
		expense2.setAmount(300);
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(expense1);
		expenses.add(expense2);
		account.setExpenses(expenses);
		
		Optional<Account> accountOptional = Optional.of(account);
		
//		When 
		when(accountRepository.findById(anyLong())).thenReturn(accountOptional);
		
//		then 
		mockMvc.perform(get("/account/1/expenses"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("id")))
			.andExpect(content().string(containsString("amount")));
		
	}
	
	
	
}
