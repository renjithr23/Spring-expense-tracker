package com.renj1thr.expensetracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.renj1thr.expensetracker.domains.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
