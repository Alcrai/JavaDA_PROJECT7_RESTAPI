package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListControllerTest {

   @Mock
   private BidListService bidListService;
   private BidListController bidListController;
   private BidList bid;
   @Mock
   private Model model;
   @Mock
   private BindingResult bindingResult;
   @Mock
   private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void initTest(){
        bidListController= new BidListController(bidListService);
        bid = new BidList("AccountTest", "TypeTest", 10d);
        bid.setBidListId(1);
    }

    @Test
    public void homeTest(){
        List<BidList> bidLists = new ArrayList<>();
        bidLists.add(bid);
        when(bidListService.findAll()).thenReturn(bidLists);
        assertThat(bidListController.home(model)).isEqualTo("bidList/list");
        verify(bidListService).findAll();
    }
    @Test
    public void addBidFormTest(){
        assertThat(bidListController.addBidForm(bid,"","",model)).isEqualTo("bidList/add");
    }
    @Test
    public void validateTest(){
        BidList bidNull= new BidList("","",10d);
        assertThat(bidListController.validate(bidNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/bidList/add");
        assertThat(bidListController.validate(bid,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("bidList/add");
    }
    @Test
    public void showUpdateFormTest(){
        when(bidListService.findById(1)).thenReturn(bid);
        assertThat(bidListController.showUpdateForm(1,"","",model)).isEqualTo("bidList/update");
        verify(bidListService).findById(1);
    }
    @Test
    public void updateBidTest(){
        BidList bidNull= new BidList("","",10d);
        assertThat(bidListController.updateBid(1,bidNull,"add",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/bidList/update/{id}");
        assertThat(bidListController.updateBid(1,bid,"cancel",bindingResult,model,redirectAttributes)).isEqualTo("redirect:/bidList/update/1");
    }
    @Test
    public void deleteBidtest(){
        when(bidListService.delete(1)).thenReturn(bid);
        assertThat(bidListController.deleteBid(1,model)).isEqualTo("redirect:/bidList/list");
        verify(bidListService).delete(1);
    }

}
