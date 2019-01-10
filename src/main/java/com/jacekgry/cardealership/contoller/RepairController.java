package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Repair;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class RepairController {

    private RepairService repairService;
    private CarDealershipService carDealershipService;
    private CarService carService;
    private CustomerService customerService;

    @GetMapping("/repairs")
    public String showRepairs(Model model,
                              @RequestParam(required = false) Integer carId,
                              @RequestParam(required = false) Integer customerId,
                              @RequestParam(required = false) Integer cdId,
                              @RequestParam(required = false, defaultValue = "") String carName,
                              @RequestParam(required = false, defaultValue = "") String customerFirstName,
                              @RequestParam(required = false, defaultValue = "") String customerLastName,
                              @RequestParam(required = false, defaultValue = "") String cdName,
                              @RequestParam(required = false, defaultValue = "all") String state
    ) {
        List<Repair> purchases = repairService.findBySearchCriteria(carId, customerId, cdId, carName, customerFirstName, customerLastName, cdName, state);
        model.addAttribute("repairs", purchases);

        model.addAttribute("carId", carId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("cdId", cdId);
        model.addAttribute("carName", carName);
        model.addAttribute("customerFirstName", customerFirstName);
        model.addAttribute("customerLastName", customerLastName);
        model.addAttribute("cdName", cdName);
        model.addAttribute("state", state);

        return "repairs";
    }

    @GetMapping("/add/repair")
    public String newRepairForm(Model model) {
        model.addAttribute("cardealerships", carDealershipService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());

        Repair repair = new Repair();
        repair.setSubmissionDate(new Date());
        model.addAttribute("repair", repair);
        return "add_repair";
    }

    @PostMapping("/add/repair")
    public String newRepairSubmit(@ModelAttribute @Valid Repair repair, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cardealerships", carDealershipService.findAll());
            model.addAttribute("cars", carService.findAll());
            model.addAttribute("customers", customerService.findAll());
            return "add_repair";
        }
        repairService.save(repair);
        return "redirect:/repairs";
    }

    @GetMapping("/edit/repair/{id}")
    public String editRepairForm(@PathVariable Integer id, Model model) {
        try {
            Repair repair = repairService.findById(id);
            model.addAttribute("cardealerships", carDealershipService.findAll());
            model.addAttribute("cars", carService.findAll());
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("repair", repair);
            return "add_repair";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @GetMapping("/repair/{id}")
    public String showRepair(@PathVariable Integer id, Model model) {
        try {
            Repair repair = repairService.findById(id);
            model.addAttribute("repair", repair);
            return "repair";
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @PostMapping("delete/repair")
    public String deleteRepair(@RequestParam Integer id) {
        repairService.deleteById(id);
        return "redirect:/repairs";
    }
}
