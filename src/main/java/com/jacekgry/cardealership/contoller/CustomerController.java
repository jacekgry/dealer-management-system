package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomers(Model model, @RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName) {
        List<Customer> customers = customerService.findByNames(firstName, lastName);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @PostMapping("/add/customer")
    public String addCustomerSubmit(@ModelAttribute @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_customer";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/add/customer")
    public String addCustomerForm(@ModelAttribute Customer customer, Model model) {
        return "add_customer";
    }

    @GetMapping("/customer/{id}")
    public String showCustomer(Model model, @PathVariable Integer id) {
        try {
            Customer customer = customerService.findById(id);
            model.addAttribute("customer", customer);
            return "customer";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @PostMapping("delete/customer")
    public String deleteCustomer(@RequestParam Integer id) {
        try {
            customerService.deleteById(id);
            return "redirect:/customers";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @GetMapping("edit/customer/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {
        try {
            Customer customer = customerService.findById(id);
            model.addAttribute("customer", customer);
            return "add_customer";
        } catch (NotFoundException e) {
            throw e;
        }
    }

}
