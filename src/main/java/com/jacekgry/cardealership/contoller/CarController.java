package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/all/cars")
    public String allCars(Model model) {
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/delete/car/{id}")
    public String deleteCar(@PathVariable int id){
        carService.deleteById(id);
        return "redirect:/all/cars";
    }

    @GetMapping("/add/car")
    public String addCarForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("fuels", carService.findAllFuels());
        return "add_car";
    }

    @PostMapping("/add/car")
    public String addCarSubmit(@ModelAttribute Car car){
        carService.saveCar(car);
        return "redirect:/all/cars";
    }
}
