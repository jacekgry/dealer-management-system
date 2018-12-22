package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Fuel;
import com.jacekgry.cardealership.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping(value = "/cars")
    public String allCars(Model model, @RequestParam(value = "phraseSearch", required = false) String phraseSearch) {
        List<Car> cars = carService.findByPhraseSearch(phraseSearch);
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/delete/car/{id}")
    public String deleteCar(@PathVariable int id) {
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
    public String addCarSubmit(@ModelAttribute Car car, @RequestParam(required = false) MultipartFile[] imgs) {

        carService.saveCar(car);

//        for(MultipartFile mf : imgs){
//            if(!mf.isEmpty()){
//                try {
//                    CarImg carImg = CarImg.builder().car(car).img(mf.getBytes()).build();
//                    carService.saveImg(carImg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        return "redirect:/cars";
    }

    @GetMapping("/edit/car/{id}")
    public String editCar(@PathVariable Integer id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("fuels", Fuel.values());
        return "add_car";
    }

    @GetMapping("/car/{id}")
    public String showCar(@PathVariable Integer id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
//        model.addAttribute("numOfImgs", car.getImgs().size());
        return "car";
    }

    @PostMapping("/cars/decrease")
    public String decreasePrices(@RequestParam String percentage){
        BigDecimal discountPercentage = new BigDecimal(percentage);
        carService.decreasePrices(discountPercentage);
        return "redirect:/cars";
    }

    @PostMapping("/cars/increase")
    public String increasePrices(@RequestParam String percentage){
        BigDecimal increasePercentage = new BigDecimal(percentage);
        carService.increasePrices(increasePercentage);
        return "redirect:/cars";
    }

    @GetMapping("/cars/ranking")
    public String carsRanking(Model model){
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
