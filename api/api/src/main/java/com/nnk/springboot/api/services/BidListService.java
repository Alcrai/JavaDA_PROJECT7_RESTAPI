package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.BidList;
import com.nnk.springboot.api.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public BidList save(BidList bid) {
        bidListRepository.save(bid);
        return bid;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList delete(Integer id) {
        BidList bid = bidListRepository.findById(id).get();
        bidListRepository.delete(bid);
        return bid;
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id).get();
    }

    public BidList update(BidList bidList,int id) {
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        return bidList;
    }
}
