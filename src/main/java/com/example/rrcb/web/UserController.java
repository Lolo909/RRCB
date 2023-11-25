package com.example.rrcb.web;

import com.example.rrcb.model.binding.CarEditBindingModel;
import com.example.rrcb.model.binding.UserProfileEditBindingModel;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String allCars(Model model) {

        //List<RouteViewModel>  routeViewModelsList = routeService.findAllRoutesView();
        model.addAttribute("users", userService.findAllUsersView());
        return "allUsersAdmin";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/users/all";
    }

    @GetMapping("/changeRole/{id}")
    public String changeRole(@PathVariable Long id) {
        //userService.remove(id);
        userService.changeRole(id);
        return "redirect:/users/all";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        //userService.remove(id);
        //userService.changeRole(id);
        //model.addAttribute("user", userService.findById(id));
        model.addAttribute("user", userService.getUserViewByUsername(principal.getName()));

        return "profileUser";
    }


    @GetMapping("/profile/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        model.addAttribute("profileForEdit", userService.findById(id));
        //model.addAttribute("car", carService.findCarById(id));
        return "user-edit";
    }

    @PatchMapping("/profile/edit/{id}")
    public String editConfirm(@Valid UserProfileEditBindingModel userProfileEditBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable Long id, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileEditBindingModel", userProfileEditBindingModel);
            //userProfileEdit !!!!!!!
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditBindingModel", bindingResult);
            return "redirect:/profile/edit/{id}";
        }

        //CarServiceModel carServiceModel = modelMapper.map(carEditBindingModel, CarServiceModel.class);

        userService.editProfile(id, userProfileEditBindingModel);

        return "redirect:/";
    }





}
