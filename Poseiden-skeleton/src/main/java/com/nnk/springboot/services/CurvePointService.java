package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * CurvePoint Service
 * this class makes the link between the controller and the repository
 *
 */
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
        if(curve!=null) {
            curvePointRepository.delete(curve);
        }
        return curve;
    }

    public CurvePoint findById(Integer id) {
        return curvePointRepository.findById(id).get();
    }

    public CurvePoint update(CurvePoint curvePoint, Integer id) {
        curvePoint.setId(id);
        curvePointRepository.save(curvePoint);
        return curvePoint;
    }
}
