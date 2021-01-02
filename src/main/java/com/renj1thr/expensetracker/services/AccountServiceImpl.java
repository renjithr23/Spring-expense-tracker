package com.renj1thr.expensetracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;

@Service
public class AccountServiceImpl implements AccountService{
	private final PersonService personService;

	public AccountServiceImpl(PersonService personService) {
		this.personService = personService;
	}
	
	@Override
	public List<Account> getAccounts(Long id){
		
		List<Account> accountList = new ArrayList<Account>();
		Person person = personService.getPersonById(id);
		person.getAccounts().iterator().forEachRemaining(accountList::add);
		
		return accountList;
	}
}
