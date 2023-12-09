package com.example.rrcb.web;

import com.example.rrcb.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("newestCarImage", carService.findNewestCarImageUrl());

        return "index";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/rentsInfo")
    public String rentInfo(){
        return "rentsInfo";
    }


}
