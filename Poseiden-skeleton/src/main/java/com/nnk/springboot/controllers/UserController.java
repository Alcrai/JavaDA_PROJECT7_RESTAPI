package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
/**
 * User Controller
 * this controller manages the display of the list of user, add, update and delete user
 */
@Log4j2
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        log.info("load List of user");
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user, @RequestParam(value = "errorpassword",defaultValue = "")String errorpassword, Model model) {
        model.addAttribute("errorpassword", errorpassword);
        log.info("Load view add User");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (!result.hasErrors()) {
            if(goodPassword(user.getPassword())) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                log.info("add new user : "+user.toString());
                model.addAttribute("users", userRepository.findAll());
                return "redirect:/user/list";
            }else{
                redirectAttributes.addAttribute("errorpassword","Password must be contain 1 uperCase, 1 number and 1 special char");
                log.error("Password must be contain 1 uperCase, 1 number and 1 special char");
                return "redirect:/user/add";
            }
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,@RequestParam(value = "errorpassword",defaultValue = "")String errorpassword, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("errorpassword", errorpassword);
        log.info("load view for update User");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/update";
        }
        if(goodPassword(user.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setId(id);
            userRepository.save(user);
            log.info("update User : "+user.toString());
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        } else{
            redirectAttributes.addAttribute("errorpassword","Password must be contain 1 uperCase, 1 number and 1 special char");
            log.error("Password must be contain 1 uperCase, 1 number and 1 special char");
            return "redirect:/user/update";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        log.info("delete user with id : "+id);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    private Boolean goodPassword(String password){
        String passwordRegex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*]).{4,}$";
        if(password.matches(passwordRegex)){
            return true;
        }else {
            return false;
        }
    }
}
