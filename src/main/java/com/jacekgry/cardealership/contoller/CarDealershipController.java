package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.service.CarDealershipService;
import com.jacekgry.cardealership.service.PurchaseService;
import com.jacekgry.cardealership.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class CarDealershipController {

    private final CarDealershipService carDealerShipService;
    private final PurchaseService purchaseService;
    private final RepairService repairService;

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

    @PostMapping("/add/cardealership")
    public String addCarDealershipSubmit(@ModelAttribute CarDealership carDealership) {
        carDealerShipService.save(carDealership);
        return "redirect:/cardealerships";
    }

    @GetMapping("/cardealership/{id}")
    public String showCarDealership(@PathVariable int id, Model model) {
        CarDealership carDealership = carDealerShipService.findById(id);
        model.addAttribute("cardealership", carDealership);
        return "cardealership";
    }

    @GetMapping("/cardealership/purchases/{id}")
    public String purchasesInCarDealership(@PathVariable int id, Model model) {
        CarDealership carDealership = carDealerShipService.findById(id);
        model.addAttribute("purchases", purchaseService.findAllByCardealership(carDealership));
        return "purchases";
    }

    @GetMapping("/cardealership/repairs/{id}")
    public String repairsInCarDealership(@PathVariable int id, Model model) {
        CarDealership carDealership = carDealerShipService.findById(id);
        model.addAttribute("repairs", repairService.findAllByCarDealership(carDealership));
        return "repairs";
    }

    @PostMapping("/delete/cardealership")
    public String deleteCustomer(@RequestParam Integer id) {
        carDealerShipService.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/cardealership/stock/{id}")
    public String showStockInCarDealership(@PathVariable int id, Model model) {
        List<Stock> stock = carDealerShipService.getStockForCardealership(id);
        model.addAttribute("stock", stock);
        model.addAttribute("cdId", id);
        return "stock";
    }

    @PostMapping("/cardealership/updatestock")
    public String updateStock(@RequestParam int cdId, @RequestParam int carId, @RequestParam int quantity){
        Stock stock = carDealerShipService.findStockByCardealershipIdAndCarId(cdId, carId);
        stock.setAvailableNumber(quantity);
        carDealerShipService.saveStock(stock);

        return "redirect:/cardealership/stock/" + cdId;
    }

}
