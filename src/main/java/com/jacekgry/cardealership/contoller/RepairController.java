package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Repair;
import com.jacekgry.cardealership.service.CarDealershipService;
import com.jacekgry.cardealership.service.CarService;
import com.jacekgry.cardealership.service.CustomerService;
import com.jacekgry.cardealership.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/customer/repairs/{id}")
    public String customerRepairs(Model model, @PathVariable int id){
        List<Repair> repairs = repairService.findCustomerRepairs(id);
        model.addAttribute("repairs", repairs);
        return "repairs";
    }

    @GetMapping("/repairs")
    public String showRepairs(Model model){
        List<Repair> repairs = repairService.findAll();
        model.addAttribute("repairs", repairs);
        return "repairs";
    }

    @GetMapping("/add/repair")
    public String newRepairForm(Model model){
        model.addAttribute("cardealerships", carDealershipService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());

        Repair repair = new Repair();
        repair.setSubmissionDate(new Date());
        model.addAttribute("repair", repair);
        return "add_repair";
    }

    @PostMapping("/add/repair")
    public String newRepairSubmit(@ModelAttribute Repair repair){
        repairService.save(repair);
        return "redirect:/repairs";
    }

    @GetMapping("/edit/repair/{id}")
    public String editRepairForm(@PathVariable Integer id, Model model){
        Repair repair = repairService.findById(id);
        model.addAttribute("cardealerships", carDealershipService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("repair", repair);
        return "add_repair";
    }

    @GetMapping("/repair/{id}")
    public String showRepair(@PathVariable Integer id, Model model){
        Repair repair = repairService.findById(id);
        model.addAttribute("repair", repair);
        return "repair";
    }
}
