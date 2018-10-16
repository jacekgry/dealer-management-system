package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<Customer> allCustomers(){
        return customerService.findAll();
    }

}
