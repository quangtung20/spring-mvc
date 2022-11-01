package com.quangtung.springbootblogwebapp.controller;

import com.quangtung.springbootblogwebapp.dto.PostDto;
import com.quangtung.springbootblogwebapp.dto.RegistrationDto;
import com.quangtung.springbootblogwebapp.entity.User;
import com.quangtung.springbootblogwebapp.repository.UserRepository;
import com.quangtung.springbootblogwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        RegistrationDto newUser = new RegistrationDto();
        model.addAttribute("user",newUser);
        return "auth/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        return "auth/login";
    }

    @PostMapping("/register/save")
    public String saveRegisterUser(
            @Valid @ModelAttribute("user")RegistrationDto registrationDto,
            BindingResult result,
            Model model
    ){
        User existingUser = userService.findUserByEmail(registrationDto.getEmail());
        if(existingUser != null && existingUser.getEmail()!=null ){
            result.rejectValue("email",null, "this email has already exists");
        }
        if(result.hasErrors()){
            model.addAttribute("user",registrationDto);
            return "auth/register";
        }
        userService.saveUser(registrationDto);
        return "redirect:/register?success";
    }
}
