package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> curvePointList = curvePointService.findAll();
        model.addAttribute("curvePoints",curvePointList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid,@RequestParam(value = "errorcurve",defaultValue = "")String errorcurve,Model model) {
        model.addAttribute("errorcurve", errorcurve);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint,@RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (curvePoint.getCurveId()<=0){
            redirectAttributes.addAttribute("errorcurve","must not be null");
            return "redirect:/curvePoint/add";
        }else {
            if (action.equals("cancel")){
               curvePoint.setCurveId(0);
               curvePoint.setTerm(0);
               curvePoint.setValue(0);
                return  "curvePoint/add";
            }
            if (action.equals("add")){
                curvePointService.save(curvePoint);
                redirect ="redirect:/curvePoint/list";
            }
        }
        return redirect;
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,@RequestParam(value = "errorcurve",defaultValue = "")String errorcurve, Model model) {
        model.addAttribute("errorcurve",errorcurve);
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint",curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (curvePoint.getCurveId()<=0){
            redirectAttributes.addAttribute("errorcurve","must not be null");
            return "redirect:/curvePoint/update/{id}";
        }else {
            if (action.equals("cancel")){
                return  "redirect:/curvePoint/update/"+id;
            }
            if (action.equals("update")){
                curvePointService.update(curvePoint,id);
                redirect ="redirect:/curvePoint/list";
            }
        }
        return redirect;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
