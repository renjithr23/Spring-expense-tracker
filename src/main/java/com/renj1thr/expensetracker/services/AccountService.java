package com.renj1thr.expensetracker.services;

import java.util.List;

import com.renj1thr.expensetracker.domains.Account;

public interface AccountService {
	List<Account> getAccounts(Long id);
}
