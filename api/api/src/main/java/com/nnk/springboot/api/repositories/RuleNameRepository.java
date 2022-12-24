package com.nnk.springboot.api.repositories;

import com.nnk.springboot.api.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
