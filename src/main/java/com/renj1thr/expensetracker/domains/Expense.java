package com.renj1thr.expensetracker.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "A Valid Amount for Expense should be present")
  private double amount;

  private String description;

  @NotNull(message = "The Valid Type for Expense should be present")
  private String type;

  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
  @JsonBackReference
  private Account account;
}
