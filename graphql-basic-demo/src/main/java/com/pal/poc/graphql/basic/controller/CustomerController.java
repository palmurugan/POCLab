package com.pal.poc.graphql.basic.controller;

import com.pal.poc.graphql.basic.entity.Customer;
import com.pal.poc.graphql.basic.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @MutationMapping
  public Customer createCustomer(@Argument Customer customer) {
    return customerService.createCustomer(customer);
  }

  @QueryMapping
  public List<Customer> getAllCustomer() {
    return customerService.getAllCustomers();
  }

  @QueryMapping
  public Customer getCustomerById(@Argument Long id) {
    return customerService.findById(id);
  }
}
