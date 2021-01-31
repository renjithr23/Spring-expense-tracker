package com.renj1thr.expensetracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.domains.Income;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j	
public class AccountServiceImpl implements AccountService{
	
	public final AccountRepository accountRepository;
	public final ExpenseService expenseService;
	public final IncomeService incomeService;
	
	public AccountServiceImpl(AccountRepository accountRepository, ExpenseService expenseService, IncomeService incomeService) {
		this.accountRepository = accountRepository;
		this.expenseService = expenseService;
		this.incomeService = incomeService;
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
		
		log.info("Account " + account.getId() + " is added");

		return account;
	}
	
	
	@Override
	public Expense addExpense(long accountId, Expense expense) {
		
		Optional<Account> accountOptional = accountRepository.findById(accountId);
		
		if(!accountOptional.isPresent()) {
			throw new RuntimeException("Account Not Found!");
		
		}
		Account account = accountOptional.get();
		
		expense = expenseService.addExpense(account, expense);
		
		log.info("Expense " + expense.getId() + " is added");
	
		return expense;
	}
	
	@Override
	public List<Expense> getExpenses(long accountId) {
		
		Optional<Account> accountOptional = accountRepository.findById(accountId);
		
		if(!accountOptional.isPresent()) {
			throw new RuntimeException("Account Not Found!");
		
		}
		Account account = accountOptional.get();
		
		log.info("Expenses for account " + accountId + " fetched");
		
		return account.getExpenses();
		
	}
	
	
	
	
}
