package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("rules",ruleNameList);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName, @RequestParam(value = "errorname",defaultValue = "")String errorname,
                              @RequestParam(value = "errordescription",defaultValue = "")String errordescription,Model model) {
        model.addAttribute("errorname",errorname);
        model.addAttribute("errordescription",errordescription);
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName,@RequestParam(value="action",required = true)String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (ruleName.getName().isBlank()||ruleName.getDescription().isBlank()){
            if (ruleName.getName().isBlank()){
                redirectAttributes.addAttribute("errorname","Name is mandatory");
            }
            if (ruleName.getDescription().isBlank()){
                redirectAttributes.addAttribute("errordescription","Description is mandatory");
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
                return  "ruleName/add";
            }
            if (action.equals("add")){
                ruleNameService.save(ruleName);
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
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,@RequestParam(value="action",required = true)String action,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String redirect = "";
        if (ruleName.getName().isBlank()||ruleName.getDescription().isBlank()){
            if (ruleName.getName().isBlank()){
                redirectAttributes.addAttribute("errorname","Name is mandatory");
            }
            if (ruleName.getDescription().isBlank()){
                redirectAttributes.addAttribute("errordescription","Description is mandatory");
            }
            return "redirect:/ruleName/update/{id}";
        }else {
            if (action.equals("cancel")){
                return  "redirect:/ruleName/update/"+id;
            }
            if (action.equals("update")){
                ruleNameService.update(ruleName,id);
                redirect ="redirect:/ruleName/list";
            }
        }
        return redirect;
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}
