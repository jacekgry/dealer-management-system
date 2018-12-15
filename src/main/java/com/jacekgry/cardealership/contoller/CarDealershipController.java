package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.service.CarDealerShipService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CarDealershipController {

    private final CarDealerShipService carDealerShipService;

    @GetMapping("/cardealerships")
    public String allCardealerships(Model model) {
        model.addAttribute("cardealerships", carDealerShipService.findAll());
        return "cardealerships";
    }

    @GetMapping("add/cardealership")
    public String addCarDealershipForm(Model model) {
        model.addAttribute("carDealership", new CarDealership());
        return "add_cardealership";
    }

    @PostMapping("add/cardealership")
    public String addCarDealershipSubmit(@ModelAttribute CarDealership carDealership) {
        carDealerShipService.save(carDealership);
        return "redirect:/cardealerships";
    }

    @GetMapping("/cardealership/{id}")
    public String showCarDealership(@PathVariable int id, Model model){
        CarDealership carDealership = carDealerShipService.findById(id);
        model.addAttribute("cardealership", carDealership);
        return "cardealership";
    }
}
