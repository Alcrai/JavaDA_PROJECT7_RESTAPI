package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.BidList;
import com.nnk.springboot.api.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Iterable<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList delete(Integer id) {
        BidList bid = bidListRepository.findById(id).get();
        bidListRepository.delete(bid);
        return bid;
    }

    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    public BidList update(BidList bidList,int id) {
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        return bidList;
    }
}
