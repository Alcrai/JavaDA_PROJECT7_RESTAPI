package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
 * BidList Controller
 * this controller manages the display of the list of rule name, add, update and delete rule name
 */
@Log4j2
@Controller
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("rules",ruleNameList);
        log.info("load List of rule name");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, @RequestParam(value = "errorname",defaultValue = "")String errorname,
                              @RequestParam(value = "errordescription",defaultValue = "")String errordescription,Model model) {
        model.addAttribute("errorname",errorname);
        model.addAttribute("errordescription",errordescription);
        log.info("Load view add rule name");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName,@RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (ruleName.getName().isBlank()||ruleName.getDescription().isBlank()){
            if (ruleName.getName().isBlank()){
                redirectAttributes.addAttribute("errorname","Name is mandatory");
                log.error("Name is mandatory");
            }
            if (ruleName.getDescription().isBlank()){
                redirectAttributes.addAttribute("errordescription","Description is mandatory");
                log.error("Description is mandatory");
            }
            return "redirect:/ruleName/add";
        }else {
            if (action.equals("cancel")){
               ruleName.setName("");
               ruleName.setDescription("");
               ruleName.setJson("");
               ruleName.setTemplate("");
               ruleName.setSqlStr("");
               ruleName.setSqlPart("");
               log.info("cancel entry");
               return  "ruleName/add";
            }
            if (action.equals("add")){
                ruleNameService.save(ruleName);
                log.info("add new Rule name : "+ruleName.toString());
                redirect ="redirect:/ruleName/list";
            }
        }
        return redirect;
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,@RequestParam(value = "errorname",defaultValue = "")String errorname,
                                 @RequestParam(value = "errordescription",defaultValue = "")String errordescription, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName",ruleName);
        model.addAttribute("errorname",errorname);
        model.addAttribute("errordescription",errordescription);
        log.info("load view for update Rule name");
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (ruleName.getName().isBlank()||ruleName.getDescription().isBlank()){
            if (ruleName.getName().isBlank()){
                redirectAttributes.addAttribute("errorname","Name is mandatory");
                log.error("Name is mandatory");
            }
            if (ruleName.getDescription().isBlank()){
                redirectAttributes.addAttribute("errordescription","Description is mandatory");
                log.error("Description is mandatory");
            }
            return "redirect:/ruleName/update/{id}";
        }else {
            if (action.equals("cancel")){
                log.info("cancel entry");
                return  "redirect:/ruleName/update/"+id;
            }
            if (action.equals("update")){
                ruleNameService.update(ruleName,id);
                log.info("update rule name : "+ruleName.toString());
                redirect ="redirect:/ruleName/list";
            }
        }
        return redirect;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
        log.info("delete rule name with id : "+id);
        return "redirect:/ruleName/list";
    }
}
