package com.renj1thr.expensetracker.domains;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

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
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "The firstname field should not be blank ")
  private String firstName;

  @NotBlank(message = "The lastname field should not be blank")
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
  private Set<Account> accounts;

  public void setAccounts(Set<Account> accounts) {
    accounts.iterator().forEachRemaining((account) -> account.setPerson(this));
    this.accounts = accounts;
  }
}
