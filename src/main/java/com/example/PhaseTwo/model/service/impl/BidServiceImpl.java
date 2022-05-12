package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.repository.BidRepository;
import com.example.PhaseTwo.model.service.BidService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class BidServiceImpl implements BidService {
    private BidRepository bidRepository;
    private OrderServiceImpl orderService;
    private ExpertServiceImpl expertService;

    public BidServiceImpl(BidRepository bidRepository, OrderServiceImpl orderService, ExpertServiceImpl expertService) {
        this.bidRepository = bidRepository;
        this.orderService = orderService;
        this.expertService = expertService;
    }

    @Override
    public Bid save(Bid bid, Expert expert, Orders orders) {
        if(bid==null){
            return null;
        }else if(bid.getBidDate()==null ||
        bid.getHoursNeeded()==null ||
        bid.getTimeToStart()==null ||
        bid.getTimeToStart().isBefore(LocalDateTime.now())) {
            return  null;
        }
        if(expert==null || orders ==null){
            return null;
        }else  if(expert.getId()<1 || orders.getId()<1){
            return null;
        }
        Expert expert1=expertService.findById(expert.getId());
        Orders orders1=orderService.findById(orders.getId());
        bid.setExpert(expert1);
        bid.setOrders(orders1);
        Bid bid1=bidRepository.save(bid);
        return bid1;
    }

    @Override
    public Bid findById(Long id) {
        return bidRepository.findById(id).orElse(null);
    }
}
