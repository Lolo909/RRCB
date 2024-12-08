package com.example.rrcb.web;

import com.example.rrcb.model.binding.UserRegisterBindingModel;
import com.example.rrcb.model.service.UserServiceModel;
import com.example.rrcb.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(PasswordEncoder passwordEncoder, UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        //TODO existing username with custom validator

        userService.registerUser(modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }



//    @GetMapping("/login")
//    public String login(Model model){
//        //model.addAttribute("isNotFound", true);
//        return "login";
//    }
//
//    @ModelAttribute
//    public UserLoginBindingModel userLoginBindingModel(){
//        return new UserLoginBindingModel();
//    }

/*
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model){

        model.addAttribute("user", modelMapper.map(userService.findById(id), UserViewModel.class));

        return "profile";
    }
*/

}
