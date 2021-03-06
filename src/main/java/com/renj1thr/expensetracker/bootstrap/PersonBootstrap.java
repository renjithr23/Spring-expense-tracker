package com.renj1thr.expensetracker.bootstrap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.renj1thr.expensetracker.domains.Account;
import com.renj1thr.expensetracker.domains.Expense;
import com.renj1thr.expensetracker.domains.Income;
import com.renj1thr.expensetracker.domains.Person;
import com.renj1thr.expensetracker.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PersonBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  public final PersonRepository personRepository;

  public PersonBootstrap(PersonRepository personRepository) {
    super();
    this.personRepository = personRepository;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    this.personRepository.saveAll(getPersons());
    log.info("Bootstrap People Saved to Database");
  }

  public List<Person> getPersons() {
    List<Person> bootstrap_persons = new ArrayList<>(2);

    List<Account> generated_accounts = getAccounts();

    Set<Account> p1AccountSet = new HashSet<Account>();
    p1AccountSet.add(generated_accounts.get(0));

    Set<Account> p2AccountSet = new HashSet<Account>();
    p2AccountSet.add(generated_accounts.get(1));

    Person person1 = Person.builder().firstName("Renjith").lastName("Ramesh").build();

    Person person2 = Person.builder().firstName("Rakesh").lastName("Sharma").build();

    person1.setAccounts(p1AccountSet);
    person2.setAccounts(p2AccountSet);

    bootstrap_persons.add(person2);
    bootstrap_persons.add(person1);

    return bootstrap_persons;
  }

  public List<Account> getAccounts() {

    List<Account> bootstrap_accounts = new ArrayList<>(2);

    Account hdfc_account =
        Account.builder()
            .balance(1000.00)
            .description("Salary Account")
            .name("HDFC_SALARY")
            .type("SALARY")
            .build();

    hdfc_account.setExpenses(getExpenses());
    hdfc_account.setIncomes(getIncomes());

    bootstrap_accounts.add(hdfc_account);

    Account sbi_account =
        Account.builder()
            .balance(100000.00)
            .description("Savings Account")
            .name("SBI_SAVINGS")
            .type("SAVINGS")
            .build();

    sbi_account.setExpenses(getExpenses());
    sbi_account.setIncomes(getIncomes());

    bootstrap_accounts.add(sbi_account);

    return bootstrap_accounts;
  }

  public List<Expense> getExpenses() {
    double[] amounts = {1000.00, 800.00, 4000.00};
    String[] descirptions = {"Buying Groceries", "Paying Electricity Bills", "School Fees"};
    String[] type = {"Food", "Household", "Education"};

    List<Expense> bootstrap_expenses = new ArrayList<Expense>();

    for (int i = 0; i < amounts.length; i++) {
      Expense expense = new Expense();
      expense.setAmount(amounts[i]);
      expense.setDescription(descirptions[i]);
      expense.setType(type[i]);

      bootstrap_expenses.add(expense);
    }

    return bootstrap_expenses;
  }

  public List<Income> getIncomes() {
    double[] amounts = {10000.00, 80000.00, 400.00};
    String[] descirptions = {"Monthly Rent Obtained", "Salary", "Interest"};
    String[] type = {"Rent", "Salary", "Earnings"};

    List<Income> bootstrap_incomes = new ArrayList<Income>();

    for (int i = 0; i < amounts.length; i++) {
      Income income = new Income();
      income.setAmount(amounts[i]);
      income.setDescription(descirptions[i]);
      income.setType(type[i]);

      bootstrap_incomes.add(income);
    }

    return bootstrap_incomes;
  }
}
