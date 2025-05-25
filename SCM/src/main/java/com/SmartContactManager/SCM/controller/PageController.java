package com.SmartContactManager.SCM.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.SmartContactManager.SCM.Entities.User;
import com.SmartContactManager.SCM.forms.UserForm;
import com.SmartContactManager.SCM.services.UserService;


@Controller
public class PageController {

    @Autowired   //also use constructor injection
    private UserService userService;

    @RequestMapping("/home")
    public String home(){
       
            System.out.println("This is home page");
            return "home";
        
    }
  
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page run");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page run");
        return "services";
    }  
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/signup")
    public String signup(Model model) {
        UserForm userform = new UserForm();
        
        model.addAttribute("userForm", userform);
        return "signup";
    } 
    
      
    //processing register form

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userform, BindingResult rbinding, Model model) {
        System.out.println("Processing");
        //fetch form data
        System.out.println(userform);

         if(rbinding.hasErrors()) {
             return "signup";
         }

        //user-service

//           User user = User.builder()
//                       .name(userform.getName())
//                       .email(userform.getEmail())
//                       .password(userform.getPassword())
//                       .phone(userform.getPhone())
//                       .build();

        User user = new User();
        user.setName(userform.getName());
        user.setEmail(userform.getEmail());
        user.setPassword(userform.getPassword());
        user.setPhone(userform.getPhone());

        try {
            User savedUser = userService.saveUser(user);
            System.out.println("User registered successfully");
            model.addAttribute("successMessage", "Registration successful!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("userForm", userform); // Preserve user input
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        System.out.println("User registered successfully: " );

        return "redirect:/signup";
    }


}
