package com.nnk.springboot.api.repositories;

import com.nnk.springboot.api.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
