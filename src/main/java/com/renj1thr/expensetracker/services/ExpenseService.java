package com.renj1thr.expensetracker.services;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;

public interface ExpenseService {
  Expense addExpense(Account account, Expense expense);
}
