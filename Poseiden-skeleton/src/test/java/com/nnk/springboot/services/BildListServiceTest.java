package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BildListServiceTest {
    @Mock
    private BidListRepository bidListRepository;
    private BidListService bidListService;
    private BidList bid;

    @BeforeEach
    public void initTest(){
        bidListService= new BidListService(bidListRepository);
        bid = new BidList("Account Test", "Type Test", 10d);
        bid.setBidListId(1);
    }

    @Test
    public void saveBidList(){
        when(bidListRepository.save(bid)).thenReturn(bid);
        assertThat(bidListService.save(bid)).isEqualTo(bid);
        verify(bidListRepository).save(bid);
    }
    @Test
    public void findAllTest(){
        List<BidList> bidList = new ArrayList<>();
        bidList.add(bid);
        when (bidListRepository.findAll()).thenReturn(bidList);
        assertThat(bidListService.findAll()).asList();
        verify(bidListRepository).findAll();
    }
    @Test
    public void deleteTest(){
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));
        assertThat(bidListService.delete(1)).isEqualTo(bid);
        verify(bidListRepository).findById(1);
    }
    @Test
    public void findByIdTest(){
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));
        assertThat(bidListService.findById(1)).isEqualTo(bid);
        verify(bidListRepository).findById(1);
    }
    @Test
    public void updatetest(){
        when(bidListRepository.save(bid)).thenReturn(bid);
        assertThat(bidListService.update(bid,1)).isEqualTo(bid);
        verify(bidListRepository).save(bid);
    }
}
