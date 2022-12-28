package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.services.RatingService;
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
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/rating")
    public Iterable<Rating> getRating(){
        log.info("Request:GET /rating");
        log.info("Response:");
        ratingService.findAll().forEach(bl->log.info(bl.toString()));
        return ratingService.findAll();
    }

    @GetMapping("/rating/{id}")
    public Optional<Rating> getRatingById(@PathVariable("id")int id){
        log.info("Request:GET /rating/"+id);
        log.info("Response:"+ratingService.findById(id).toString());
        return ratingService.findById(id);
    }

    @PostMapping("/rating")
    public ResponseEntity<Rating> postRating(@RequestBody Rating rating){
        Rating ratingAdd = ratingService.save(rating);
        if(Objects.isNull(ratingAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(ratingAdd)
                .toUri();
        log.info("Request:POST /rating" + ratingAdd);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity<Rating> putCurvePoint(@PathVariable("id")int id,@RequestBody Rating rating){
        Rating ratingAdd= ratingService.update(rating,id);
        if(Objects.isNull(ratingAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(ratingAdd)
                .toUri();
        log.info("Request:PUT /rating/"+id + rating);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/rating/{id}")
    public Map<String,Boolean> deleteCurvePoint(@PathVariable("id") int id){
        Rating ratingAdd = ratingService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /rating/"+ id);
        return response;
    }

}
