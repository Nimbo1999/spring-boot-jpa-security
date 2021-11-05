package io.github.nimbo1999.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.nimbo1999.domain.entity.Customer;
import io.github.nimbo1999.rest.dto.CreateCustomerDTO;
import io.github.nimbo1999.service.CustomerService;
import io.github.nimbo1999.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerServiceImpl service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        return service.saveCustomer(createCustomerDTO);
    }

}
