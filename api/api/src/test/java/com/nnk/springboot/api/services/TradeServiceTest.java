package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
    @Mock
    private TradeRepository tradeRepository;
    private TradeService tradeService;
    private Trade trade;

    @BeforeEach
    public void initTest(){
        tradeService= new TradeService(tradeRepository);
        trade = new Trade("Trade Account", "Type");
        trade.setTradeId(1);
    }

    @Test
    public void saveTest(){
        when(tradeRepository.save(trade)).thenReturn(trade);
        assertThat(tradeService.save(trade)).isEqualTo(trade);
        verify(tradeRepository).save(trade);
    }
    @Test
    public void findAllTest(){
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);
        when (tradeRepository.findAll()).thenReturn(tradeList);
        assertThat(tradeService.findAll()).asList();
        verify(tradeRepository).findAll();
    }
    @Test
    public void deleteTest(){
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        assertThat(tradeService.delete(1)).isEqualTo(trade);
        verify(tradeRepository).findById(1);
    }
    @Test
    public void findByIdTest(){
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        assertThat(tradeService.findById(1)).isEqualTo(trade);
        verify(tradeRepository).findById(1);
    }
    @Test
    public void updatetest(){
        when(tradeRepository.save(trade)).thenReturn(trade);
        assertThat(tradeService.update(trade,1)).isEqualTo(trade);
        verify(tradeRepository).save(trade);
    }
}
