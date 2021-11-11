package io.github.nimbo1999.service;

import java.util.List;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CustomerDTO;
import io.github.nimbo1999.rest.dto.PageParamsDTO;

public interface CustomerService {
    public Customer saveCustomer(CustomerDTO customer);
    public List<Customer> listCustomers(PageParamsDTO pageParams);
    public Customer getById(Long id);
    public Customer updateCustomer(Long id, CustomerDTO customer);
    public void deleteCustomerById(Long id);
    public Long customerCount();
}
