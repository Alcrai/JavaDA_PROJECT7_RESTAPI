package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Optional<Trade> findById(Integer id) {
        return tradeRepository.findById(id);
    }

    public Trade update(Trade trade, Integer id) {
        trade.setTradeId(id);
        return tradeRepository.save(trade);
    }

    public Trade delete(Integer id) {
        Trade trade = tradeRepository.findById(id).get();
        tradeRepository.delete(trade);
        return trade;
    }
}
