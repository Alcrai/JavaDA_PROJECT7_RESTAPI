package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public CurvePoint save(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
        return curvePoint;
    }

    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    public CurvePoint delete(Integer id) {
        CurvePoint curve = curvePointRepository.findById(id).get();
        curvePointRepository.delete(curve);
        return curve;
    }

    public Optional<CurvePoint> findById(Integer id) {
        return curvePointRepository.findById(id);
    }

    public CurvePoint update(CurvePoint curvePoint, Integer id) {
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        return curvePoint;
    }
}
