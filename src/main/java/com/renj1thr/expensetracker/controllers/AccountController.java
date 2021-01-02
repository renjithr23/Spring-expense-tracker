package com.renj1thr.expensetracker.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.services.AccountService;
import com.renj1thr.expensetracker.services.PersonService;

@RestController
public class AccountController {
	public final PersonService personService;
	public final AccountService accountService;
	
	
	public AccountController(PersonService personService, AccountService accountService) {
		this.personService = personService;
		this.accountService = accountService;
	}

	@GetMapping
	@RequestMapping("/person/{id}/accounts")
	public List<Account> getAccounts(@PathVariable("id") long id){
		return this.accountService.getAccounts(id);
	}
	
}
