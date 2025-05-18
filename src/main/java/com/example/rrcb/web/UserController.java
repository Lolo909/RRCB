package com.example.rrcb.web;

import com.example.rrcb.model.binding.UserProfileEditBindingModel;
import com.example.rrcb.service.OrderService;
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
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, OrderService orderService, ModelMapper modelMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String allUsers(Model model) {

        model.addAttribute("users", userService.findAllUsersView());
        return "allUsersAdmin";
    }

    @GetMapping("/rents")
    public String rents(Model model, Principal principal) {

        model.addAttribute("rents", orderService.findAllRentsOfTheUserByName(principal.getName()));

        return "rents";
    }
    @GetMapping("/allRents")
    public String allRents(Model model) {

        model.addAttribute("allRents", orderService.findAllRents());
        return "allRentsAdmin";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/users/all";
    }

    @GetMapping("/rents/cancel/{id}")
    public String removeOrderWithinXDaysAway(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderService.removeOrderWithIdWithinXDaysAway(id, 7);
            redirectAttributes.addFlashAttribute("successMessage", "Rent successfully cancelled.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/users/rents";
    }

    @GetMapping("/rents/remove/{id}")
    public String removeOrder(@PathVariable Long id) {
        orderService.removeOrderWithId(id);
        return "redirect:/users/rents";
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
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditBindingModel", bindingResult);
            return "redirect:/profile/edit/{id}";
        }

        //CarServiceModel carServiceModel = modelMapper.map(carEditBindingModel, CarServiceModel.class);

        userService.editProfile(id, userProfileEditBindingModel);

        return "redirect:/";
    }





}
