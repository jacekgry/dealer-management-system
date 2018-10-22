package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.service.AddressService;
import com.jacekgry.cardealership.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;

    @GetMapping("/all/customers")
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }

    @PostMapping("/add/customer")
    public String addCustomerSubmit(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "customers";
    }

    @GetMapping("/add/customer")
    public String addCustomerForm(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("addresses", addressService.findAll());
        return "add_customer";
    }

    @GetMapping("/customer/{id}")
    public String showCustomer(Model model, @PathVariable Integer id) {

        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute("customer", customer.orElseThrow(() -> new CustomerNotFoundException("id - " + id)));

        return "customer";
    }




    @ExceptionHandler(CustomerNotFoundException.class)
    public ModelAndView handleCustomerNotFoundException(CustomerNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject()
        return modelAndView;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException(String msg) {
            super(msg);
        }
    }
}
