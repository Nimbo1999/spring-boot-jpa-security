package io.github.nimbo1999.service.impl;

import org.springframework.stereotype.Service;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.domain.repository.CustomerRepository;
import io.github.nimbo1999.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }
    
}
