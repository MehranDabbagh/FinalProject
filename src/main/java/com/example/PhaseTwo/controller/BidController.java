package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.service.impl.BidServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bid")
public class BidController {
    private BidServiceImpl bidService;

    public BidController(BidServiceImpl bidService) {
        this.bidService = bidService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Bid> findById(@PathVariable("id") Long id) {
        Bid services = bidService.findById(id);
        if (services != null) {
            return ResponseEntity.ok(services);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Bid> save(@RequestBody Bid bid) {
        if (checkingInputObject(bid)) {
            Bid orders1 = bidService.save(bid, bid.getExpert(), bid.getOrders());
            if (orders1 != null) {
                return ResponseEntity.ok(orders1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Bid> update(@RequestBody Bid orders) {
        if (checkingInputObject(orders)) {
            bidService.update(orders);
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bid> delete(@PathVariable("id") Long id) {
        Bid orders = bidService.findById(id);
        if (orders != null) {
            bidService.delete(orders);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Bid>> findAll() {
        return ResponseEntity.ok(bidService.findAll());
    }


    private Boolean checkingInputObject(Bid orders) {
        if (orders.getOrders() == null ||
                orders.getBidDate() == null ||
                orders.getTimeToStart().isBefore(LocalDateTime.now()) ||
                orders.getExpert() == null ||
                orders.getHoursNeeded() == null ||
                orders.getBidDate() == null ||
                orders.getOffer() == null) {
            return false;
        }
        return true;
    }
}

