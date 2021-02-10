package com.renj1thr.expensetracker.repositories;

import org.springframework.data.repository.CrudRepository;

import com.renj1thr.expensetracker.domains.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {}
