package com.renj1thr.expensetracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

	AccountServiceImpl accountService;
	PersonServiceImpl personService;
	
	@Mock
	PersonRepository personRepository;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		personService = new PersonServiceImpl(personRepository);
		accountService = new AccountServiceImpl(personService);
	}

	@Test
	void getAccounts() {
		
//		Given
		
		Person person = new Person();
		person.setId(1L);
		Set<Account> accounts = new HashSet<Account>();
		Account account1 = new Account();
		account1.setId(1L);
		accounts.add(account1);
		Account account2 = new Account();
		account2.setId(2L);
		accounts.add(account2);
		person.setAccounts(accounts);

		Optional<Person> personOptional = Optional.of(person);
		when(personRepository.findById(anyLong())).thenReturn(personOptional);
		
		
//		Tests 
		
		List<Account> returnedAccounts = accountService.getAccounts(1L);
		assertEquals(returnedAccounts.size(), 2);
		verify(personRepository,times(1)).findById(anyLong());
		
		
	}
	

}
