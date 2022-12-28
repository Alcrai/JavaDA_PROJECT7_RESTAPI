package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.BidList;
import com.nnk.springboot.api.services.BidListService;
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
public class BidListController {
    @Autowired
    private BidListService bidListService;

    @GetMapping("/bidList")
    public Iterable<BidList> getBidList(){
        log.info("Request:GET /bidList");
        log.info("Response:");
        bidListService.findAll().forEach(bl->log.info(bl.toString()));
        return bidListService.findAll();
    }
    @GetMapping("/bidList/{id}")
    public Optional<BidList> getBidListById(@PathVariable("id")int id){
        log.info("Request:GET /bidList/"+id);
        log.info("Response:"+bidListService.findById(id).toString());
        return bidListService.findById(id);
    }

    @PostMapping("/bidList")
    public ResponseEntity<BidList> postBidList(@RequestBody BidList bidList){
        BidList bidAdd = bidListService.save(bidList);
        if(Objects.isNull(bidAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(bidAdd)
                .toUri();
        log.info("Request:POST /bidList" + bidList);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/bidList/{id}")
    public ResponseEntity<BidList> putBidList(@PathVariable("id")int id,@RequestBody BidList bidList){
        BidList bidAdd = bidListService.update(bidList,id);
        if(Objects.isNull(bidAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(bidAdd)
                .toUri();
        log.info("Request:PUT /bidList/"+id + bidAdd);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/bidList/{id}")
    public Map<String,Boolean> deleteBidList(@PathVariable("id") int id){
        BidList bidDelete = bidListService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /bidList/"+ id);
        return response;
    }


}
