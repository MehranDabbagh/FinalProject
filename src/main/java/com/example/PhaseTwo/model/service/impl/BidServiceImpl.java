package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Status;
import com.example.PhaseTwo.model.entity.dto.ExpertDto;
import com.example.PhaseTwo.model.repository.BidRepository;
import com.example.PhaseTwo.model.service.BidService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;

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
        if (bid.getTimeToStart().isBefore(LocalDateTime.now())) {
            throw new InputMismatchException("date is already gone!");
        }
        ExpertDto expert1 = expertService.findById(expert.getId());
        Orders orders1 = orderService.findById(orders.getId());
        if (expert1 == null || orders1 == null) {
            throw new NullPointerException("wrong id!");
        }
        bid.setExpert(expertService.convertingToExpert(expert1));
        bid.setOrders(orders1);
        Bid bid1 = bidRepository.save(bid);
        return bid1;
    }

    @Override
    public Bid findById(Long id) {
        Bid bid = bidRepository.findById(id).orElse(null);
        if (bid != null) {
            return bid;
        }
        throw new NullPointerException("wrong id!");

    }

    @Override
    public Bid update(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public void delete(Bid bid) {
        bidRepository.deleteById(bid.getId());
    }

    @Override
    public List<Bid> sortByPrice(Long id) {
        return bidRepository.sortByPrice(id);
    }

    @Override
    public List<Bid> sortByExpertPoint(Long id) {
        return bidRepository.sortByExpertPoint(id);
    }

    @Override
    public void selectingFromBids(Long bidId, Long orderId) {
        Bid bid = findById(bidId);
        if (bid == null) {
            throw new NullPointerException("wrong id!");
        }
        if (bid.getOrders().getId() != orderId) {
            throw new InputMismatchException("these two id dose not match!");
        }
        bid.setAccepted(true);
        orderService.findById(orderId).setStatus(Status.OnTheWay);
        save(bid, bid.getExpert(), bid.getOrders());
    }
}
