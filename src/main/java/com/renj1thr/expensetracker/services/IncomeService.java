package com.renj1thr.expensetracker.services;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Income;

public interface IncomeService {
	Income addIncome(Account account, Income income);
}
