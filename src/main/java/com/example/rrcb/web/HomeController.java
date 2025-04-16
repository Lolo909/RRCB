package com.example.rrcb.web;

import com.example.rrcb.service.CarService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String index(HttpServletRequest request, Model model){
        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
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
