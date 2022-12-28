package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.domain.RuleName;
import com.nnk.springboot.api.services.CurvePointService;
import com.nnk.springboot.api.services.RuleNameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@RestController
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    @GetMapping("/ruleName")
    public Iterable<RuleName> getRuleName(){
        log.info("Request:GET /ruleName");
        log.info("Response:");
        ruleNameService.findAll().forEach(bl->log.info(bl.toString()));
        return ruleNameService.findAll();
    }

    @GetMapping("/ruleName/{id}")
    public Optional<RuleName> getRuleNameById(@PathVariable("id")int id){
        log.info("Request:GET /ruleName/"+id);
        log.info("Response:"+ruleNameService.findById(id).toString());
        return ruleNameService.findById(id);
    }

    @PostMapping("/ruleName")
    public ResponseEntity<RuleName> postRuleName(@RequestBody RuleName ruleName){
        RuleName ruleAdd = ruleNameService.save(ruleName);
        if(Objects.isNull(ruleAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(ruleAdd)
                .toUri();
        log.info("Request:POST /ruleName" + ruleName);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/ruleName/{id}")
    public ResponseEntity<RuleName> putRuleName(@PathVariable("id")int id,@RequestBody RuleName ruleName){
        RuleName ruleAdd= ruleNameService.update(ruleName,id);
        if(Objects.isNull(ruleAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(ruleAdd)
                .toUri();
        log.info("Request:PUT /ruleName/"+id + ruleName);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/ruleName/{id}")
    public Map<String,Boolean> deleteRuleName(@PathVariable("id") int id){
        RuleName ruleName = ruleNameService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /ruleName/"+ id);
        return response;
    }

}
