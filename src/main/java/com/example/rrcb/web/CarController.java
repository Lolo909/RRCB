package com.example.rrcb.web;

import com.example.rrcb.model.binding.CarAddBindingModel;
import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.OrderAddBindingModel;
import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.OrderDay;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.service.CarServiceModel;
import com.example.rrcb.model.view.CarDetailsViewModel;
import com.example.rrcb.model.view.CarRentViewModel;
import com.example.rrcb.service.CarService;
import com.example.rrcb.service.CategoryService;
import com.example.rrcb.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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
    public String allCars(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "search", required = false) String search, // Add search parameter
            Model model) {

        Page<CarDetailsViewModel> carPage;

        if (search != null && !search.isEmpty()) {
            // Perform a search
            carPage = carService.searchCars(search, PageRequest.of(page, size));  //Create this function on the carService.java
        } else {
            // No search term, just get all cars with pagination
            carPage = carService.findAllCarsView(PageRequest.of(page, size));
        }

        model.addAttribute("cars", carPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", carPage.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("search", search); // Add search to model
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

        carServiceModel.setFile("data:image/jpeg;base64," + Base64.getEncoder().encodeToString(carAddBindingModel.getFile().getBytes()));

        //carServiceModel.setImageUrl(StringUtils.cleanPath(carAddBindingModel.getFile().getOriginalFilename()));
        //TODO ADD EXCEPTION ABOUT NULL IMAGE
        //carServiceModel.setImageUrl(carAddBindingModel.getFile().toString());
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

        CarRentViewModel carForRent = carService.findCarForRentById(id);
        carForRent.setImageUrl(carService.findCarById(id).getFile());//?TODO: optimize not to do two zaqvki

        carForRent.setOrderedDays(carService.getAllOrderedDays(id));
        //TODO:FIX getting all orderedDays();
        List<String> test = carForRent.getOrderedDays();
        List<String> test2 = List.of("02/15/2025", "02/20/2025");
        System.out.println("-test-");
        System.out.println(test);
        System.out.println("-----");

        model.addAttribute("carForRent", carForRent);

        return "car-rent";
    }

    @PatchMapping("/rent/{id}")
    public String rentConfirm(@Valid OrderAddBindingModel orderAddBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable Long id, Model model, Principal principal,  @RequestParam("date") String selectedDates) throws IOException{

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:/cars/rent/{id}";
        }

        List<String> dateList = Arrays.stream(selectedDates.split(","))
                .map(String::trim).toList();

        System.out.println(dateList);
        orderAddBindingModel.setAllOrderDays(dateList);

        carService.rent(id, orderAddBindingModel, principal);

        return "redirect:/cars/all";
    }



        /*

        @Scheduled(cron = "59 59 23 L * ?")
        public void monthlyUpdateOfCars(){
            //System.out.println(LocalDateTime.now());
                orderService.ClearUp();
                carService.updateOfCarsAllAvailableDays();

        }

        */
}
