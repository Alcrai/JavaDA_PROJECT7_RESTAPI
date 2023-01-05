package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {
    @Mock
    private RuleNameService ruleNameService;
    private RuleNameController ruleNameController;
    private RuleName rule;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void initTest(){
        ruleNameController= new RuleNameController(ruleNameService);
        rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
    }

    @Test
    public void homeTest(){
        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(rule);
        when(ruleNameService.findAll()).thenReturn(ruleNameList);
        assertThat(ruleNameController.home(model)).isEqualTo("ruleName/list");
        verify(ruleNameService).findAll();
    }
    @Test
    public void addBidFormTest(){
        assertThat(ruleNameController.addRuleForm(rule,"","",model)).isEqualTo("ruleName/add");
    }
    @Test
    public void validateTest(){
        RuleName ruleNull = new RuleName("", "", "", "", "", "SQL Part");
        assertThat(ruleNameController.validate(ruleNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/ruleName/add");
        assertThat(ruleNameController.validate(rule,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("ruleName/add");
    }
    @Test
    public void showUpdateFormTest(){
        when(ruleNameService.findById(1)).thenReturn(rule);
        assertThat(ruleNameController.showUpdateForm(1,"","",model)).isEqualTo("ruleName/update");
        verify(ruleNameService).findById(1);
    }
    @Test
    public void updateBidTest(){
        RuleName ruleNull = new RuleName("", "", "", "", "", "SQL Part");
        assertThat(ruleNameController.updateRuleName(1,ruleNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/ruleName/update/{id}");
        assertThat(ruleNameController.updateRuleName(1,rule,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/ruleName/update/1");
    }
    @Test
    public void deleteBidtest(){
        when(ruleNameService.delete(1)).thenReturn(rule);
        assertThat(ruleNameController.deleteRuleName(1,model)).isEqualTo("redirect:/ruleName/list");
        verify(ruleNameService).delete(1);
    }
}
