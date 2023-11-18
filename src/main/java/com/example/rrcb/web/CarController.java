package com.example.rrcb.web;

import com.example.rrcb.model.binding.CarAddBindingModel;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.service.CarService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

/*
    @GetMapping("/all")
    public String allCars(Model model){

        //List<RouteViewModel>  routeViewModelsList = routeService.findAllRoutesView();
        model.addAttribute("cars", carService.findAllCarsView());
        return "allCars";
    }
    */

/*
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        model.addAttribute("route", carService.findCarById(id));
        return "route-details";
    }
*/

    @GetMapping("/add")
    public String add(){
        return "add-car";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CarAddBindingModel carAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("routeAddBindingModel", carAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        CarServiceModel carServiceModel = modelMapper.map(carAddBindingModel, CarServiceModel.class);
        //carServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        //TODO oprui do krai dobavqneto na kolata
        carService.addNewCar(carServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public CarAddBindingModel carAddBindingModel(){
        return new CarAddBindingModel();
    }
}
