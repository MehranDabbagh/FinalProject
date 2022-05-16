package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Expert;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Status;
import com.example.PhaseTwo.model.repository.BidRepository;
import com.example.PhaseTwo.model.service.BidService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (bid == null) {
            return null;
        } else if (bid.getBidDate() == null ||
                bid.getHoursNeeded() == null ||
                bid.getTimeToStart() == null ||
                bid.getTimeToStart().isBefore(LocalDateTime.now())) {
            return null;
        }
        if (expert == null || orders == null) {
            return null;
        } else if (expert.getId() < 1 || orders.getId() < 1) {
            return null;
        }
        Expert expert1 = expertService.findById(expert.getId());
        Orders orders1 = orderService.findById(orders.getId());
        bid.setExpert(expert1);
        bid.setOrders(orders1);
        Bid bid1 = bidRepository.save(bid);
        return bid1;
    }

    @Override
    public Bid findById(Long id) {
        return bidRepository.findById(id).orElse(null);
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
            System.out.println("there is no bid with this id!");
            return;
        }
        if (bid.getOrders().getId() != orderId) {
            System.out.println("this bid is not for this order!");
            return;
        }
        bid.setAccepted(true);
        orderService.findById(orderId).setStatus(Status.OnTheWay);
        save(bid, bid.getExpert(), bid.getOrders());
    }
}
