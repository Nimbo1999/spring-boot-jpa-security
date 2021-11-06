package io.github.nimbo1999.service;

import java.util.List;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CustomerDTO;

public interface CustomerService {
    public Customer saveCustomer(CustomerDTO customer);
    public List<Customer> listCustomers();
}
