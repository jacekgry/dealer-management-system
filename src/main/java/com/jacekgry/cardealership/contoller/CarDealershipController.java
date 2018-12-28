package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.CarDealershipService;
import com.jacekgry.cardealership.service.PurchaseService;
import com.jacekgry.cardealership.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class CarDealershipController {

    private final CarDealershipService carDealerShipService;

    @GetMapping("/cardealerships")
    public String allCarDealerships(Model model) {
        model.addAttribute("cardealerships", carDealerShipService.findAll());
        return "cardealerships";
    }

    @GetMapping("/add/cardealership")
    public String addCarDealershipForm(Model model) {
        model.addAttribute("carDealership", new CarDealership());
        return "add_cardealership";
    }

    @GetMapping("/edit/cardealership/{id}")
    public String editCarDealershipForm(Model model, @PathVariable Integer id) {
        try {
            CarDealership carDealership = carDealerShipService.findById(id);
            model.addAttribute("carDealership", carDealership);
            return "add_cardealership";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @PostMapping("/add/cardealership")
    public String addCarDealershipSubmit(@ModelAttribute @Valid CarDealership carDealership, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_cardealership";
        }
        carDealerShipService.save(carDealership);
        return "redirect:/cardealerships";
    }

    @GetMapping("/cardealership/{id}")
    public String showCarDealership(@PathVariable int id, Model model) {
        try {
            CarDealership carDealership = carDealerShipService.findById(id);
            model.addAttribute("cardealership", carDealership);
            return "cardealership";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @PostMapping("/delete/cardealership")
    public String deleteCustomer(@RequestParam Integer id, Model model) {
        carDealerShipService.deleteById(id);
        return "redirect:/cardealerships";
    }

    @GetMapping("/cardealership/stock/{id}")
    public String showStockInCarDealership(@PathVariable int id, Model model) {
        List<Stock> stock = carDealerShipService.getStockForCardealership(id);
        model.addAttribute("stock", stock);
        model.addAttribute("cdId", id);
        model.addAttribute("stockObj", new Stock(carDealerShipService.findById(id)));
        return "stock";
    }

    @PostMapping("/cardealership/updatestock")
    public String updateStock(@ModelAttribute @Valid Stock stockObj) {
        carDealerShipService.saveStock(stockObj);
        return "redirect:/cardealership/stock/" + stockObj.getCdId();
    }
}