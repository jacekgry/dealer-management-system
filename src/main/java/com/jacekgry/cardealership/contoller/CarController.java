package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
