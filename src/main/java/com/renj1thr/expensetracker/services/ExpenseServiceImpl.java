package com.renj1thr.expensetracker.services;

import org.springframework.stereotype.Service;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.repositories.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

  public final ExpenseRepository expenseRepository;

  public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Expense addExpense(Account account, Expense expense) {

    if (expense.getAmount() < 0) expense.setAmount(0);
    expense.setAccount(account);
    expense = expenseRepository.save(expense);

    return expense;
  }
}
