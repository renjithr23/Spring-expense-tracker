package com.renj1thr.expensetracker.services;

import java.util.List;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.domains.Income;
import com.renj1thr.expensetracker.domains.Person;

public interface AccountService {

  Account addAccount(Account account, Person person);

  Expense addExpense(long accountId, Expense expense);

  List<Expense> getExpenses(long accountId);

  Income addIncome(long accountId, Income income);

  List<Income> getIncomes(long accountId);
}
