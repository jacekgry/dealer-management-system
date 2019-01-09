package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.error.DeletionException;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.error.NotSufficientStockException;
import com.jacekgry.cardealership.service.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PurchasesController {

    private PurchaseService purchaseService;
    private CarDealershipService carDealerShipService;
    private CarService carService;
    private CustomerService customerService;
    private RepairService repairService;

    @GetMapping("/customer/purchases/{id}")
    public String customerPurchases(Model model, @PathVariable Integer id) {
        List<Purchase> purchases = purchaseService.findCustomerPurchases(id);
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    @GetMapping("/purchases")
    public String findPurchases(Model model,
                                @RequestParam(required = false) Integer carId,
                                @RequestParam(required = false) Integer customerId,
                                @RequestParam(required = false) Integer cdId,
                                @RequestParam(required = false, defaultValue = "") String carName,
                                @RequestParam(required = false, defaultValue = "") String customerFirstName,
                                @RequestParam(required = false, defaultValue = "") String customerLastName,
                                @RequestParam(required = false, defaultValue = "") String cdName
    ) {
        List<Purchase> purchases = purchaseService.findBySearchCriteria(carId, customerId, cdId, carName, customerFirstName, customerLastName, cdName);
        model.addAttribute("purchases", purchases);

        model.addAttribute("carId", carId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("cdId", cdId);
        model.addAttribute("carName", carName);
        model.addAttribute("customerFirstName", customerFirstName);
        model.addAttribute("customerLastName", customerLastName);
        model.addAttribute("cdName", cdName);

        return "purchases";
    }

    @PostMapping("/delete/purchase")
    public String deletePurchase(@RequestParam Integer id) throws Exception {
        try {
            purchaseService.deleteById(id);
            return "redirect:/purchases";
        } catch (DataIntegrityViolationException e) {
            Map<String, String> associatedEntities = new HashMap<>();
            associatedEntities.put("repairs", repairService.repairsAssociatedWithPurchase(id));
            throw new DeletionException("purchase", id, associatedEntities);
        }
    }

    @GetMapping("/add/purchase")
    public String newPurchaseForm(Model model) {
        model.addAttribute("cardealerships", carDealerShipService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("purchase", new Purchase());
        return "add_purchase";
    }

    @PostMapping("/add/purchase")
    public String newPurchaseSubmit(@ModelAttribute @Valid Purchase purchase, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cardealerships", carDealerShipService.findAll());
            model.addAttribute("cars", carService.findAll());
            model.addAttribute("customers", customerService.findAll());
            return "add_purchase";
        }
        try {
            purchase.setPrice(purchase.getCar().getPrice());
            purchaseService.save(purchase);
            return "redirect:/purchases";
        } catch (NotSufficientStockException e) {
            throw e;
        }
    }

    @GetMapping("/purchase/{id}")
    public String showPurchaseDetails(@PathVariable int id, Model model) {
        try {
            Purchase purchase = purchaseService.findById(id);
            model.addAttribute("purchase", purchase);
            return "purchase";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @GetMapping(value = "/car_purchases", produces = "application/json")
    @ResponseBody
    public List<Purchase> purchasesOfCar(@RequestParam Integer car) {
        List<Purchase> purchases = purchaseService.findByCarId(car);
        return purchases;
    }
}
