package com.renj1thr.expensetracker.services;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Income;
import com.renj1thr.expensetracker.repositories.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService {
	public final IncomeRepository incomeRepository;

	public IncomeServiceImpl(IncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}
	
	@Override
	public Income addIncome(Account account, Income income) {
		income.setAccount(account);
		incomeRepository.save(income);
		
		return income;
	}
}
