package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.service.CarDealershipService;
import com.jacekgry.cardealership.service.CarService;
import com.jacekgry.cardealership.service.CustomerService;
import com.jacekgry.cardealership.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PurchasesController {

    private PurchaseService purchaseService;
    private CarDealershipService carDealerShipService;
    private CarService carService;
    private CustomerService customerService;

    @GetMapping("/customer/purchases/{id}")
    public String customerPurchases(Model model, @PathVariable Integer id){
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
                                ){
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
    public String deletePurchase(@RequestParam Integer id){
        purchaseService.deleteById(id);
        return "redirect:/purchases";
    }

    @GetMapping("/add/purchase")
    public String newPurchaseForm(Model model){
        model.addAttribute("cardealerships", carDealerShipService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("purchase", new Purchase());
        return "add_purchase";
    }

    @PostMapping("/add/purchase")
    public String newPurchaseSubmit(@ModelAttribute Purchase purchase){
        purchase.setPrice(purchase.getCar().getPrice());
        purchaseService.save(purchase);
        return "redirect:/purchases";
    }

    @GetMapping("/purchase/{id}")
    public String showPurchaseDetails(@PathVariable int id, Model model){
        Purchase purchase = purchaseService.findById(id);
        model.addAttribute("purchase", purchase);
        return "purchase";
    }

}
