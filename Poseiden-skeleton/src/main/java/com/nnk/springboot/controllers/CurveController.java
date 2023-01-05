package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
 * Curve Controller
 * this controller manages the display of the list of curve, add, update and delete curve
 */
@Log4j2
@Controller
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> curvePointList = curvePointService.findAll();
        model.addAttribute("curvePoints",curvePointList);
        log.info("load List of CurvePoint");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint bid,@RequestParam(value = "errorcurve",defaultValue = "")String errorcurve,Model model) {
        model.addAttribute("errorcurve", errorcurve);
        log.info("Load view add CurvePoint");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint,@RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (curvePoint.getCurveId()<=0){
            redirectAttributes.addAttribute("errorcurve","must not be null");
            log.error("must not be null");
            return "redirect:/curvePoint/add";
        }else {
            if (action.equals("cancel")){
               curvePoint.setCurveId(0);
               curvePoint.setTerm(0);
               curvePoint.setValue(0);
                log.info("cancel entry");
                return  "curvePoint/add";
            }
            if (action.equals("add")){
                curvePointService.save(curvePoint);
                log.info("add new CurvePoint : "+curvePoint.toString());
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
        log.info("load view for update CurvePoint");
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (curvePoint.getCurveId()<=0){
            redirectAttributes.addAttribute("errorcurve","must not be null");
            log.error("must not be null");
            return "redirect:/curvePoint/update/{id}";
        }else {
            if (action.equals("cancel")){
                log.info("cancel entry");
                return  "redirect:/curvePoint/update/"+id;
            }
            if (action.equals("update")){
                curvePointService.update(curvePoint,id);
                log.info("update CurvePoint : " + curvePoint.toString());
                redirect ="redirect:/curvePoint/list";
            }
        }
        return redirect;
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        log.info("delete CurvePoint with id : "+id);
        return "redirect:/curvePoint/list";
    }
}
