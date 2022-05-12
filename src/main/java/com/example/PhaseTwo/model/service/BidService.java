package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;

public interface BidService {
    Bid save(Bid bid, Expert expert, Orders orders);
   Bid findById(Long id);
}
