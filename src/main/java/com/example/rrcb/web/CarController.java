package com.example.rrcb.web;

import com.example.rrcb.model.binding.CarAddBindingModel;
import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.service.CarService;
import com.example.rrcb.service.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CarController(CarService carService, CategoryService categoryService, ModelMapper modelMapper) {
        this.carService = carService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String allCars(Model model) {

        //List<RouteViewModel>  routeViewModelsList = routeService.findAllRoutesView();
        model.addAttribute("cars", carService.findAllCarsView());
        return "allCars";
    }

    //allCarsAdmin
    @GetMapping("/allCarsAdmin")
    public String allCarsAdmin(Model model) {

        //List<RouteViewModel>  routeViewModelsList = routeService.findAllRoutesView();
        model.addAttribute("cars", carService.findAllCarsView());
        return "allCarsAdmin";
    }

    @GetMapping("/vintage")
    public String allVintageCars(Model model) {

        model.addAttribute("cars", carService.findAllCarsViewByCategory(CategoryNameEnum.VINTAGE));
        return "allVintageCars";
    }

    @GetMapping("/antique")
    public String allAntiqueCars(Model model) {

        model.addAttribute("cars", carService.findAllCarsViewByCategory(CategoryNameEnum.ANTIQUE));
        return "allAntiqueCars";
    }

    @GetMapping("/classic")
    public String allClassicCars(Model model) {

        model.addAttribute("cars", carService.findAllCarsViewByCategory(CategoryNameEnum.CLASSIC));
        return "allClassicCars";
    }



/*
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        model.addAttribute("route", carService.findCarById(id));
        return "route-details";
    }
*/

    @GetMapping("/add")
    public String add() {
        return "add-car";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CarAddBindingModel carAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("carAddBindingModel", carAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:add";
        }

        CarServiceModel carServiceModel = modelMapper.map(carAddBindingModel, CarServiceModel.class);
        Integer year = carServiceModel.getCreated();
        if (year >= 1919 && year <= 1930) {
            carServiceModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.VINTAGE));
        } else if (year <= 1975) {
            carServiceModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.ANTIQUE));
        } else {
            carServiceModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.CLASSIC));
        }

        //TODO vseki mesec da se setva na novo

        //carServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        //TODO oprui dokrai dobavqneto na kolata
        carService.addNewCar(carServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public CarAddBindingModel carAddBindingModel() {
        return new CarAddBindingModel();
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        carService.remove(id);
        return "redirect:/cars/allCarsAdmin";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        model.addAttribute("car", carService.findCarById(id));
        return "car-details";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        model.addAttribute("carForEdit", carService.findCarById(id));
        //model.addAttribute("car", carService.findCarById(id));
        return "car-edit";
    }


    @PatchMapping("/edit/{id}")
    public String editConfirm(@Valid CarEditBindingModel carEditBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable Long id, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("carEditBindingModel", carEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:/cars/edit/{id}";
        }

        //CarServiceModel carServiceModel = modelMapper.map(carEditBindingModel, CarServiceModel.class);
        Integer year = carEditBindingModel.getCreated();
        if (year >= 1919 && year <= 1930) {
            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.VINTAGE));
        } else if (year <= 1975) {
            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.ANTIQUE));
        } else {
            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.CLASSIC));
        }

        carService.editCar(id, carEditBindingModel);

        return "redirect:/cars/allCarsAdmin";
    }

    @GetMapping("/rent/{id}")
    public String rent(@PathVariable Long id, Model model){

        //Car car = carService.findCarForRentById(id);
        //List<Integer> allAvailableDays = carService.getAllAvailableDays(car.getDays().getAllAvailableDays(), car.getDays().getAllOrderedDays());

        CarRentViewModel test = carService.findCarForRentById(id);
        model.addAttribute("carForRent", test);//TODO debug id=null
        //model.addAttribute("carID", id);
        //model.addAttribute("days", allAvailableDays);


        //model.addAttribute("car", carService.findCarById(id));
        return "car-rent";
    }

    @PatchMapping("/rent/{id}")
    public String rentConfirm(@Valid OrderAddBindingModel orderAddBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable Long id, Model model, Principal principal) throws IOException{

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:/cars/rent/{id}";
        }

        //TODO vseki mesec da se setva na novo

        //carServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        carService.rent(id, orderAddBindingModel, principal);

        return "redirect:/cars/all";
    }

//    @PatchMapping("/rent/{id}")
//    public String rentConfirm(@Valid CarEditBindingModel carEditBindingModel, BindingResult bindingResult,
//                              RedirectAttributes redirectAttributes, @PathVariable Long id, Model model) throws IOException {
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("carEditBindingModel", carEditBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
//            return "redirect:add";
//        }
//
//        //CarServiceModel carServiceModel = modelMapper.map(carEditBindingModel, CarServiceModel.class);
//        Integer year = carEditBindingModel.getCreated();
//        if (year >= 1919 && year <= 1930) {
//            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.VINTAGE));
//        } else if (year <= 1975) {
//            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.ANTIQUE));
//        } else {
//            carEditBindingModel.setCategory(categoryService.findCategoryByName(CategoryNameEnum.CLASSIC));
//        }
//
//        carService.editCar(id, carEditBindingModel);
//
//        return "redirect:/cars/allCarsAdmin";
//    }

}
