package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;

import java.util.List;

public interface BidService {
    Bid save(Bid bid, Expert expert, Orders orders);
   Bid findById(Long id);
    List<Bid> sortByPrice(Long id);
    List<Bid> sortByExpertPoint(Long id);
}
