package com.nnk.springboot.api.controllers;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.services.CurvePointService;
import com.nnk.springboot.api.services.TradeService;
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
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/trade")
    public Iterable<Trade> getTrade(){
        log.info("Request:GET /trade");
        log.info("Response:");
        tradeService.findAll().forEach(bl->log.info(bl.toString()));
        return tradeService.findAll();
    }

    @GetMapping("/trade/{id}")
    public Optional<Trade> getTradeById(@PathVariable("id")int id){
        log.info("Request:GET /trade/"+id);
        log.info("Response:"+tradeService.findById(id).toString());
        return tradeService.findById(id);
    }

    @PostMapping("/trade")
    public ResponseEntity<Trade> postTrade(@RequestBody Trade trade){
        Trade tradeAdd = tradeService.save(trade);
        if(Objects.isNull(tradeAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(tradeAdd)
                .toUri();
        log.info("Request:POST /trade" + trade);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/trade/{id}")
    public ResponseEntity<Trade> putCurvePoint(@PathVariable("id")int id,@RequestBody Trade trade){
        Trade tradeAdd= tradeService.update(trade,id);
        if(Objects.isNull(tradeAdd)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(tradeAdd)
                .toUri();
        log.info("Request:PUT /trade/"+id + trade);
        log.info("Response:"+ ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/trade/{id}")
    public Map<String,Boolean> deleteCurvePoint(@PathVariable("id") int id){
        Trade trade = tradeService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Request:DELETE /trade/"+ id);
        return response;
    }

}
