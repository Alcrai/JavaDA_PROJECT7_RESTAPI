package com.nnk.springboot.api.repositories;

import com.nnk.springboot.api.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
