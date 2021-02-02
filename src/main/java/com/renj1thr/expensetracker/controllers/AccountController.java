package com.renj1thr.expensetracker.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
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
	
	@PostMapping
	@RequestMapping("/person/{id}/accounts/add")
	public Account addAccount(@Valid @RequestBody Account account, @PathVariable("id") long id) {
		return this.personService.addAccount(account, id);
	}
	
	@PostMapping
	@RequestMapping("/account/{id}/expenses/add")
	public Expense addExpense(@Valid @RequestBody Expense expense, @PathVariable("id") long accountId) {
		return this.accountService.addExpense(accountId, expense);
	}
	
	@GetMapping
	@RequestMapping("/account/{id}/expenses")
	public ResponseEntity<List<Expense>> getExpenses(@PathVariable("id") long id){
		return ResponseEntity.ok(this.accountService.getExpenses(id));
	}
	
}
