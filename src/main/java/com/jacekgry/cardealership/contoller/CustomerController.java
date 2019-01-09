package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.error.DeletionException;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.CustomerService;
import com.jacekgry.cardealership.service.PurchaseService;
import com.jacekgry.cardealership.service.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final PurchaseService purchaseService;
    private final RepairService repairService;

    @GetMapping("/customers")
    public String showCustomers(Model model, @RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName) {
        List<Customer> customers = customerService.findByNames(firstName, lastName);
        model.addAttribute("customers", customers);

        model.addAttribute("customerFirstName", firstName);
        model.addAttribute("customerLastName", lastName);

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
    public String deleteCustomer(@RequestParam Integer id) throws DeletionException {
        try {
            customerService.deleteById(id);
            return "redirect:/customers";
        } catch (DataIntegrityViolationException e){
            Map<String, String> associatedEntities = new HashMap<>();
            associatedEntities.put("purchases", purchaseService.purchasesAssociatedWithCustomer(id));
            associatedEntities.put("repairs", repairService.repairsAssociatedWithCustomer(id));
            throw new DeletionException("customer", id, associatedEntities);
        }
        catch (NotFoundException e) {
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
