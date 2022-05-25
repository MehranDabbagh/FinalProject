package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.service.impl.BidServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        Bid bid = bidService.findById(id);
        if (bid != null) {
            return ResponseEntity.ok(bid);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Bid> save(@Valid @RequestBody Bid bid) {
            Bid bid1 = bidService.save(bid, bid.getExpert(), bid.getOrders());
            if (bid1 != null) {
                return ResponseEntity.ok(bid1);
            }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Bid> update(@Valid @RequestBody Bid bid) {

            bidService.update(bid);
            return ResponseEntity.ok(bid);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bid> delete(@PathVariable("id") Long id) {
        Bid bid = bidService.findById(id);
        if (bid != null) {
            bidService.delete(bid);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Bid>> findAll() {
        return ResponseEntity.ok(bidService.findAll());
    }



}

