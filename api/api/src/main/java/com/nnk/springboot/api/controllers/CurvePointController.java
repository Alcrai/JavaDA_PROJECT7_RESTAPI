package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.services.CurvePointService;
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
public class CurvePointController {
    @Autowired
    private CurvePointService curvePointService;

    @GetMapping("/curvePoint")
    public Iterable<CurvePoint> getCurvePoint(){
        log.info("Request:GET /curvePoint");
        log.info("Response:");
        curvePointService.findAll().forEach(bl->log.info(bl.toString()));
        return curvePointService.findAll();
    }

    @GetMapping("/curvePoint/{id}")
    public Optional<CurvePoint> getCurvePointById(@PathVariable("id")int id){
        log.info("Request:GET /curvePoint/"+id);
        log.info("Response:"+curvePointService.findById(id).toString());
        return curvePointService.findById(id);
    }

    @PostMapping("/curvePoint")
    public ResponseEntity<CurvePoint> postCurvePoint(@RequestBody CurvePoint curvePoint){
        CurvePoint curve = curvePointService.save(curvePoint);
        if(Objects.isNull(curve)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(curve)
                .toUri();
        log.info("Request:POST /curvePoint" + curve);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/curvePoint/{id}")
    public ResponseEntity<CurvePoint> putCurvePoint(@PathVariable("id")int id,@RequestBody CurvePoint curvePoint){
        CurvePoint curve= curvePointService.update(curvePoint,id);
        if(Objects.isNull(curve)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(curve)
                .toUri();
        log.info("Request:PUT /curvePoint/"+id + curve);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/curvePoint/{id}")
    public Map<String,Boolean> deleteCurvePoint(@PathVariable("id") int id){
        CurvePoint curvePoint = curvePointService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /curvePoint/"+ id);
        return response;
    }


}

