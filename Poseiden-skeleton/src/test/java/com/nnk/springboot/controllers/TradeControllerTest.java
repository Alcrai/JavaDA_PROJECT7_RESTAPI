package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeControllerTest {
    @Mock
    private TradeService tradeService;
    private TradeController tradeController;
    private Trade trade;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void initTest(){
        tradeController= new TradeController(tradeService);
        trade = new Trade("Trade Account", "Type");
        trade.setTradeId(1);
    }

    @Test
    public void homeTest(){
        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade);
        when(tradeService.findAll()).thenReturn(tradeList);
        assertThat(tradeController.home(model)).isEqualTo("trade/list");
        verify(tradeService).findAll();
    }
    @Test
    public void addTradeFormTest(){
        assertThat(tradeController.addTrade(trade,"","",model)).isEqualTo("trade/add");
    }
    @Test
    public void validateTest(){
        Trade tradeNull= new Trade("","");
        assertThat(tradeController.validate(tradeNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/trade/add");
        assertThat(tradeController.validate(trade,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("trade/add");
    }
    @Test
    public void showUpdateFormTest(){
        when(tradeService.findById(1)).thenReturn(trade);
        assertThat(tradeController.showUpdateForm(1,"","",model)).isEqualTo("trade/update");
        verify(tradeService).findById(1);
    }
    @Test
    public void updateTradeTest(){
        Trade tradeNull= new Trade("","");
        assertThat(tradeController.updateTrade(1,tradeNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/trade/update/{id}");
        assertThat(tradeController.updateTrade(1,trade,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/trade/update/1");
    }
    @Test
    public void deleteTradetest(){
        when(tradeService.delete(1)).thenReturn(trade);
        assertThat(tradeController.deleteTrade(1,model)).isEqualTo("redirect:/trade/list");
        verify(tradeService).delete(1);
    }

}
