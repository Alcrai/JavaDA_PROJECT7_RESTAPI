package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * RuleName Service
 * this class makes the link between the controller and the repository
 *
 */
@Service
public class RuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).get();
    }

    public RuleName update(RuleName ruleName, Integer id) {
        ruleName.setId(id);
        return ruleNameRepository.save(ruleName);
    }

    public RuleName delete(Integer id) {
        RuleName ruleName = ruleNameRepository.findById(id).get();
        if (ruleName != null) {
            ruleNameRepository.delete(ruleName);
        }
        return ruleName;
    }
}
