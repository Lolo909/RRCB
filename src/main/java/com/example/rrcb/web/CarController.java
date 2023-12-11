package com.example.rrcb.web;

import com.example.rrcb.model.binding.CarAddBindingModel;
import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.service.CarService;
import com.example.rrcb.service.CategoryService;
import com.example.rrcb.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final OrderService orderService;


    public CarController(CarService carService, CategoryService categoryService, ModelMapper modelMapper, OrderService orderService) {
        this.carService = carService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
    }


    @GetMapping("/all")
    public String allCars(Model model) {

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

        CarRentViewModel test = carService.findCarForRentById(id);
        model.addAttribute("carForRent", test);//TODO debug id=null

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

        carService.rent(id, orderAddBindingModel, principal);

        return "redirect:/cars/all";
    }





        @Scheduled(cron = "59 59 23 L * ?")
        public void monthlyUpdateOfCars(){
            //System.out.println(LocalDateTime.now());
                orderService.ClearUp();
                carService.updateOfCarsAllAvailableDays();

        }
}
