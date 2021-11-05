package io.github.nimbo1999.service;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CreateCustomerDTO;

public interface CustomerService {
    public Customer saveCustomer(CreateCustomerDTO customer);
}
