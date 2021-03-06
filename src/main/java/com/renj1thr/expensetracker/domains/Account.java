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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Builder.Default private double balance = 0;

  @NotBlank(message = "The name for an Account should not be blank")
  private String name;

  private String description;

  @NotBlank(message = "The Type for an Account should not be blank")
  private String type;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<Income> incomes;

  @ManyToOne
  @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
  @JsonBackReference
  private Person person;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
  private List<Expense> expenses;

  public void setIncomes(List<Income> incomes) {
    for (Income income : incomes) {
      income.setAccount(this);
    }
    this.incomes = incomes;
  }

  public void setExpenses(List<Expense> expenses) {
    for (Expense expense : expenses) {
      expense.setAccount(this);
    }
    this.expenses = expenses;
  }
}
