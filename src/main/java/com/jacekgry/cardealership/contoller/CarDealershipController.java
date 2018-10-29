package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.service.CarDealerShipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CarDealershipController {

    private final CarDealerShipService carDealerShipService;

    @GetMapping("all/cardealerships")
    public String allCardealerships(Model model) {
        model.addAttribute("cardealerships", carDealerShipService.findAll());
        return "cardealerships";
    }


}
