package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {
    @Mock
    private RuleNameRepository ruleNameRepository;
    private RuleNameService ruleNameService;
    private RuleName rule;

    @BeforeEach
    public void initTest(){
        ruleNameService= new RuleNameService(ruleNameRepository);
        rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rule.setId(1);
    }

    @Test
    public void saveTest(){
        when(ruleNameRepository.save(rule)).thenReturn(rule);
        assertThat(ruleNameService.save(rule)).isEqualTo(rule);
        verify(ruleNameRepository).save(rule);
    }
    @Test
    public void findAllTest(){
        List<RuleName> ruleList = new ArrayList<>();
        ruleList.add(rule);
        when (ruleNameRepository.findAll()).thenReturn(ruleList);
        assertThat(ruleNameService.findAll()).asList();
        verify(ruleNameRepository).findAll();
    }
    @Test
    public void deleteTest(){
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));
        assertThat(ruleNameService.delete(1)).isEqualTo(rule);
        verify(ruleNameRepository).findById(1);
    }
    @Test
    public void findByIdTest(){
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));
        assertThat(ruleNameService.findById(1)).isEqualTo(rule);
        verify(ruleNameRepository).findById(1);
    }
    @Test
    public void updatetest(){
        when(ruleNameRepository.save(rule)).thenReturn(rule);
        assertThat(ruleNameService.update(rule,1)).isEqualTo(rule);
        verify(ruleNameRepository).save(rule);
    }
}
