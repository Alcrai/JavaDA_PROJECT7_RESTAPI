package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
/**
 * Rating Controller
 * this controller manages the display of the list of rating, add, update and delete rating
 */
@Log4j2
@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> ratingList = ratingService.findAll();
        model.addAttribute("ratings", ratingList);
        log.info("load List of rating");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating, @RequestParam(value = "errormoodys",defaultValue = "")String errormoodys,
                                @RequestParam(value = "errorsand",defaultValue = "")String errorsand,
                                @RequestParam(value = "errorfitch",defaultValue = "")String errorfitch,Model model) {
        model.addAttribute("errormoodys",errormoodys);
        model.addAttribute("errorsand",errorsand);
        model.addAttribute("errorfitch",errorfitch);
        log.info("Load view add rating");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, @RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (rating.getMoodysRating().isBlank()||rating.getSandPRating().isBlank()||rating.getFitchRating().isBlank()){
            if (rating.getMoodysRating().isBlank()){
                redirectAttributes.addAttribute("errormoodys","Moodys Rating is mandatory");
                log.error("Moodys Rating is mandatory");
            }
            if (rating.getSandPRating().isBlank()){
                redirectAttributes.addAttribute("errorsand","Sand P Rating is mandatory");
                log.error("Sand P Rating is mandatory");
            }
            if (rating.getFitchRating().isBlank()){
                redirectAttributes.addAttribute("errorfitch","Fitch rating is mandatory");
                log.error("Fitch rating is mandatory");
            }
            return "redirect:/rating/add";
        }else {
            if (action.equals("cancel")){
                rating.setMoodysRating("");
                rating.setSandPRating("");
                rating.setFitchRating("");
                rating.setOrderNumber(0);
                log.info("cancel entry");
                return  "rating/add";
            }
            if (action.equals("add")){
                ratingService.save(rating);
                log.info("add new rating : "+rating.toString());
                redirect ="redirect:/rating/list";
            }
        }
        return redirect;
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, @RequestParam(value = "errormoodys",defaultValue = "")String errormoodys,
                                 @RequestParam(value = "errorsand",defaultValue = "")String errorsand,
                                 @RequestParam(value = "errorfitch",defaultValue = "")String errorfitch, Model model) {
        Rating rating = ratingService.findById(id);
        model.addAttribute("rating",rating);
        model.addAttribute("errormoodys",errormoodys);
        model.addAttribute("errorsand",errorsand);
        model.addAttribute("errorfitch",errorfitch);
        log.info("load view for update rating");
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (rating.getMoodysRating().isBlank()||rating.getSandPRating().isBlank()||rating.getFitchRating().isBlank()){
            if (rating.getMoodysRating().isBlank()){
                redirectAttributes.addAttribute("errormoodys","Moodys Rating is mandatory");
                log.error("Moodys Rating is mandatory");
            }
            if (rating.getSandPRating().isBlank()){
                redirectAttributes.addAttribute("errorsand","Sand P Rating is mandatory");
                log.error("Sand P Rating is mandatory");
            }
            if (rating.getFitchRating().isBlank()){
                redirectAttributes.addAttribute("errorfitch","Fitch rating is mandatory");
                log.error("Fitch rating is mandatory");
            }
            return "redirect:/rating/update/{id}";
        }else {
            if (action.equals("cancel")){
                log.info("cancel entry");
                return  "redirect:/rating/update/"+id;
            }
            if (action.equals("update")){
                ratingService.update(rating,id);
                log.info("update Rating : "+rating.toString());
                redirect ="redirect:/rating/list";
            }
        }
        return redirect;
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.delete(id);
        log.info("delete Rating with id : "+id);
        return "redirect:/rating/list";
    }
}
