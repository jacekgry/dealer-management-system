package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Fuel;
import com.jacekgry.cardealership.error.DeletionException;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.CarService;
import com.jacekgry.cardealership.service.PurchaseService;
import com.jacekgry.cardealership.service.RepairService;
import com.jacekgry.cardealership.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    private final PurchaseService purchaseService;
    private final RepairService repairService;

    @GetMapping(value = "/cars")
    public String allCars(Model model,
                          @RequestParam(value = "name", required = false, defaultValue = "") String name,
                          @RequestParam(required = false, defaultValue = "") String minPrice,
                          @RequestParam(required = false, defaultValue = "") String maxPrice) {

        BigDecimal min = Utils.parseBigDecimal(minPrice, "Minimum price");
        BigDecimal max = Utils.parseBigDecimal(maxPrice, "Maximum price", true);

        List<Car> cars = carService.findByNameAndPrice(name, min, max);

        model.addAttribute("cars", cars);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "cars";
    }

    @PostMapping("/delete/car")
    public String deleteCar(@RequestParam int id) throws Exception {
        try {
            carService.deleteById(id);
            return "redirect:/cars";
        } catch (DataIntegrityViolationException e) {
            Map<String, String> associatedEntities = new HashMap<>();
            associatedEntities.put("purchases", purchaseService.purchasesAssociatedWithCar(id));
            associatedEntities.put("repairs", repairService.repairsAssociatedWithCar(id));
            throw new DeletionException("car", id, associatedEntities);
        }
    }

    @GetMapping("/add/car")
    public String addCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("fuels", Fuel.values());
        return "add_car";
    }

    @PostMapping("/add/car")
    public String addCarSubmit(@ModelAttribute @Valid Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fuels", Fuel.values());
            return "add_car";
        }
        carService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/edit/car/{id}")
    public String editCar(@PathVariable Integer id, Model model) {
        try {
            Car car = carService.findById(id);
            model.addAttribute("car", car);
            model.addAttribute("fuels", Fuel.values());
            return "add_car";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/car/{id}")
    public String showCar(@PathVariable Integer id, Model model) {
        try {
            Car car = carService.findById(id);
            model.addAttribute("car", car);
            return "car";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @PostMapping("/cars/decrease")
    public String decreasePrices(@RequestParam String percentage) throws Exception {
        BigDecimal decreasePercentage;
        try {
            decreasePercentage = new BigDecimal(percentage);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Percentage must be a number");
        }
        if (decreasePercentage.compareTo(new BigDecimal(0)) > 0
                && decreasePercentage.compareTo(new BigDecimal(100)) < 0) {
            carService.decreasePrices(decreasePercentage);
        } else {
            throw new Exception("Percentage must be greater than 0 and less than 100");
        }
        return "redirect:/cars";
    }

    @PostMapping("/cars/increase")
    public String increasePrices(@RequestParam String percentage) throws Exception {
        BigDecimal increasePercentage;
        try {
            increasePercentage = new BigDecimal(percentage);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Percentage must be a number");
        }

        if (increasePercentage.compareTo(new BigDecimal(0)) > 0
                && increasePercentage.compareTo(new BigDecimal(999)) <= 0) {
            carService.increasePrices(increasePercentage);
        } else {
            throw new Exception("Percentage must be greater than 0 and less than 1000");
        }
        return "redirect:/cars";
    }

    @GetMapping("/cars/ranking")
    public String carsRanking(Model model) {
        List<Map.Entry<Car, Double>> ranking = carService.getCarsRankingByRepairsPurchasesRatio();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }
}
