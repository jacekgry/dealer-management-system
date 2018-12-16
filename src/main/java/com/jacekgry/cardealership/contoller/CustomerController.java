package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }

    @PostMapping("/add/customer")
    public String addCustomerSubmit(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    @GetMapping("/add/customer")
    public String addCustomerForm(@ModelAttribute Customer customer, Model model) {
        return "add_customer";
    }

    @GetMapping("/customer/{id}")
    public String showCustomer(Model model, @PathVariable Integer id) {

        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute("customer", customer.orElseThrow(() -> new CustomerNotFoundException("id - " + id)));

        return "customer";
    }

    @PostMapping("delete/customer")
    public String deleteCustomer(@RequestParam Integer id){
        customerService.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("edit/customer/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        Customer customer = customerService.findById(id).get();  //TODO
        model.addAttribute("customer", customer);

        return "add_customer";
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
