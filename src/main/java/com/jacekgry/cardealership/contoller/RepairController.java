package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Repair;
import com.jacekgry.cardealership.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class RepairController {

    private RepairService repairService;

    @GetMapping("/customer/repairs/{id}")
    public String customerRepairs(Model model, @PathVariable int id){
        List<Repair> repairs = repairService.findCustomerRepairs(id);
        model.addAttribute("repairs", repairs);
        return "repairs";
    }

}
