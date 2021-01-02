package com.renj1thr.expensetracker.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double balance;
	private String name;
	private String description;
	private String type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Income> incomes;
	
	@ManyToOne
	@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
	@JsonBackReference
	private Person person;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Expense> expenses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		for(Income income : incomes) {
			income.setAccount(this);
		}
		this.incomes = incomes;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		for(Expense expense : expenses) {
			expense.setAccount(this);
		}
		this.expenses = expenses;
	}
	
	


}
