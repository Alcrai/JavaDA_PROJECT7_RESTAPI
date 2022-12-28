package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.domain.User;
import com.nnk.springboot.api.services.TradeService;
import com.nnk.springboot.api.services.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Iterable<User> getTrade(){
        log.info("Request:GET /user");
        log.info("Response:");
        userService.findAll().forEach(bl->log.info(bl.toString()));
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getTradeById(@PathVariable("id")int id){
        log.info("Request:GET /user/"+id);
        log.info("Response:"+userService.findById(id).toString());
        return userService.findById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<User> postTrade(@RequestBody User user){
        User userAdd = userService.save(user);
        if(Objects.isNull(userAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(userAdd)
                .toUri();
        log.info("Request:POST /user" + user);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> putCurvePoint(@PathVariable("id")int id, @RequestBody User user){
        User userAdd= userService.update(user,id);
        if(Objects.isNull(userAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(userAdd)
                .toUri();
        log.info("Request:PUT /user/"+id + user);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/user/{id}")
    public Map<String,Boolean> deleteCurvePoint(@PathVariable("id") int id){
        User user = userService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /user/"+ id);
        return response;
    }

}
