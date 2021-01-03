package com.renj1thr.expensetracker.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	
	@PostMapping
	@RequestMapping("/person/{id}/accounts/add")
	public Account addAccount(@Valid @RequestBody Account account, @PathVariable("id") long id) {
		
		return this.personService.addAccount(account, id);
	}
	
	
//	Method for sending custom error messages for JSON Request validation errors 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
