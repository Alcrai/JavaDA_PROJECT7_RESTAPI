package com.nnk.springboot.api.repositories;

import com.nnk.springboot.api.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
