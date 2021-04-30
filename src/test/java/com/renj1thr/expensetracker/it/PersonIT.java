package com.renj1thr.expensetracker.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonIT {

  @Autowired private MockMvc mockMvc;

  @Test
  @Order(1)
  public void getPersons() throws Exception {

    String expected =
        "[{\"id\":1,\"firstName\":\"Rakesh\",\"lastName\":\"Sharma\",\"accounts\":[{\"id\":1,\"balance\":100000.0,\"name\":\"SBI_SAVINGS\",\"description\":\"Savings Account\",\"type\":\"SAVINGS\",\"incomes\":[{\"id\":1,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":2,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":3,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":1,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":2,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":3,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]},{\"id\":2,\"firstName\":\"Renjith\",\"lastName\":\"Ramesh\",\"accounts\":[{\"id\":2,\"balance\":1000.0,\"name\":\"HDFC_SALARY\",\"description\":\"Salary Account\",\"type\":\"SALARY\",\"incomes\":[{\"id\":4,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":5,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":6,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":4,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":5,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":6,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]}]";

    this.mockMvc
        .perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(expected));
  }

  @Test
  @Order(2)
  public void getPerson() throws Exception {

    String expected =
        "{\"id\":1,\"firstName\":\"Rakesh\",\"lastName\":\"Sharma\",\"accounts\":[{\"id\":1,\"balance\":100000.0,\"name\":\"SBI_SAVINGS\",\"description\":\"Savings Account\",\"type\":\"SAVINGS\",\"incomes\":[{\"id\":1,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":2,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":3,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":1,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":2,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":3,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]}";

    this.mockMvc
        .perform(get("/person/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(expected));
  }

  @Test
  @Order(3)
  public void addPerson() throws Exception {

    String request_body =
        "{\"id\":3,\"firstName\":\"Sreelekha\",\"lastName\":\"Ramesh\",\"accounts\":[]}";

    String expected_response =
        "{\"id\":3,\"firstName\":\"Sreelekha\",\"lastName\":\"Ramesh\",\"accounts\":[]}";

    this.mockMvc
        .perform(post("/persons").contentType("application/json").content(request_body))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(expected_response));

    String response_before_adding =
        "[{\"id\":1,\"firstName\":\"Rakesh\",\"lastName\":\"Sharma\",\"accounts\":[{\"id\":1,\"balance\":100000.0,\"name\":\"SBI_SAVINGS\",\"description\":\"Savings Account\",\"type\":\"SAVINGS\",\"incomes\":[{\"id\":1,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":2,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":3,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":1,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":2,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":3,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]},{\"id\":2,\"firstName\":\"Renjith\",\"lastName\":\"Ramesh\",\"accounts\":[{\"id\":2,\"balance\":1000.0,\"name\":\"HDFC_SALARY\",\"description\":\"Salary Account\",\"type\":\"SALARY\",\"incomes\":[{\"id\":4,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":5,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":6,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":4,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":5,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":6,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]}]";

    String response_after_adding =
        "[{\"id\":1,\"firstName\":\"Rakesh\",\"lastName\":\"Sharma\",\"accounts\":[{\"id\":1,\"balance\":100000.0,\"name\":\"SBI_SAVINGS\",\"description\":\"Savings Account\",\"type\":\"SAVINGS\",\"incomes\":[{\"id\":1,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":2,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":3,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":1,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":2,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":3,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]},{\"id\":2,\"firstName\":\"Renjith\",\"lastName\":\"Ramesh\",\"accounts\":[{\"id\":2,\"balance\":1000.0,\"name\":\"HDFC_SALARY\",\"description\":\"Salary Account\",\"type\":\"SALARY\",\"incomes\":[{\"id\":4,\"amount\":10000.0,\"description\":\"Monthly Rent Obtained\",\"type\":\"Rent\"},{\"id\":5,\"amount\":80000.0,\"description\":\"Salary\",\"type\":\"Salary\"},{\"id\":6,\"amount\":400.0,\"description\":\"Interest\",\"type\":\"Earnings\"}],\"expenses\":[{\"id\":4,\"amount\":1000.0,\"description\":\"Buying Groceries\",\"type\":\"Food\"},{\"id\":5,\"amount\":800.0,\"description\":\"Paying Electricity Bills\",\"type\":\"Household\"},{\"id\":6,\"amount\":4000.0,\"description\":\"School Fees\",\"type\":\"Education\"}]}]},{\"id\":3,\"firstName\":\"Sreelekha\",\"lastName\":\"Ramesh\",\"accounts\":[]}]";

    this.mockMvc
        .perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(response_after_adding));

    this.mockMvc.perform(delete("/person/3")).andExpect(status().isAccepted());

    this.mockMvc
        .perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(response_before_adding));
  }
}
