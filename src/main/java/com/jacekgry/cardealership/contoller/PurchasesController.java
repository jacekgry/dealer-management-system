package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class PurchasesController {

    private PurchaseService purchaseService;

    @GetMapping("/customer/purchases/{id}")
    public String customerPurchases(Model model, @PathVariable Integer id){
        List<Purchase> purchases = purchaseService.findCustomerPurchases(id);
        model.addAttribute("purchases", purchases);
        return "purchases";
    }
}
