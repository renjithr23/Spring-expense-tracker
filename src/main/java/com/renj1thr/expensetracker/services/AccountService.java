package com.renj1thr.expensetracker.services;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;

public interface AccountService {
	
	Account addAccount(Account account, Person person);
	
}
