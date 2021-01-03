package com.renj1thr.expensetracker.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.domains.Income;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	public final AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account addAccount(Account account, Person person){
		
//		Doesn't allow a account to be created with negative balance
		if(account.getBalance() < 0) account.setBalance(0);
		
		account.setDescription("");
		account.setIncomes(new ArrayList<Income>());
		account.setExpenses(new ArrayList<Expense>());
		account.setPerson(person);
		
		account = accountRepository.save(account);

		return account;
		
	}
}
