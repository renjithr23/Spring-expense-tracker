package com.renj1thr.expensetracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.renj1thr.expensetracker.domains.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}
