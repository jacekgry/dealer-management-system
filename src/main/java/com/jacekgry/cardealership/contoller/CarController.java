package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Fuel;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping(value = "/cars")
    public String allCars(Model model, @RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "0") BigDecimal minPrice, @RequestParam(required = false, defaultValue = "99999999") BigDecimal maxPrice) {
        List<Car> cars = carService.findByNameAndPrice(name, minPrice, maxPrice);
        model.addAttribute("cars", cars);
        return "cars";
    }

    @PostMapping("/delete/car")
    public String deleteCar(@RequestParam int id) {
        carService.deleteById(id);
        return "redirect:/cars";
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
    public String decreasePrices(@RequestParam String percentage) {
        BigDecimal discountPercentage = new BigDecimal(percentage);
        carService.decreasePrices(discountPercentage);
        return "redirect:/cars";
    }

    @PostMapping("/cars/increase")
    public String increasePrices(@RequestParam String percentage) {
        BigDecimal increasePercentage = new BigDecimal(percentage);
        carService.increasePrices(increasePercentage);
        return "redirect:/cars";
    }

    @GetMapping("/cars/ranking")
    public String carsRanking(Model model) {
        List<Map.Entry<Car, Double>> ranking = carService.getCarsRankingByRepairsPurchasesRatio();
        model.addAttribute("ranking", ranking);
        return "ranking";
    }


//    @ResponseBody
//    @GetMapping("/car/image/{id}/{which}")
//    public byte[] getCarImages(@PathVariable Integer id, @PathVariable Integer which) {
//        Car car = carService.findById(id);
//        return car.getImgs().get(which).getImg();
//    }

}
