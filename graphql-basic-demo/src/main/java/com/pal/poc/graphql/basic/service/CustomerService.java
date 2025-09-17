package com.pal.poc.graphql.basic.service;

import com.pal.poc.graphql.basic.entity.Customer;
import com.pal.poc.graphql.basic.repository.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  /**
   * Saves a given customer to the database.
   *
   * @param customer the customer to be saved
   * @return the saved customer
   */
  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer findById(Long id) {
    return customerRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
  }

}
