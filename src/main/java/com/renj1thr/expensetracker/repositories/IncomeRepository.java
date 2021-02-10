package com.renj1thr.expensetracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.renj1thr.expensetracker.domains.Income;

public interface IncomeRepository extends CrudRepository<Income, Long> {}
