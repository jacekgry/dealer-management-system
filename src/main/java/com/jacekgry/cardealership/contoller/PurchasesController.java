package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.service.CarDealerShipService;
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
    private CarDealerShipService carDealerShipService;
    private CarService carService;
    private CustomerService customerService;

    @GetMapping("/customer/purchases/{id}")
    public String customerPurchases(Model model, @PathVariable Integer id){
        List<Purchase> purchases = purchaseService.findCustomerPurchases(id);
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    @GetMapping("/purchases")
    public String findPurchases(Model model, String phraseSearch){
        List<Purchase> purchases = purchaseService.findByPhraseSearch(phraseSearch);
        model.addAttribute("purchases", purchases);
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
