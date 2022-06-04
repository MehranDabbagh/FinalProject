package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Bid;
import com.example.PhaseTwo.model.entity.Comment;
import com.example.PhaseTwo.model.entity.Orders;
import com.example.PhaseTwo.model.entity.Status;
import com.example.PhaseTwo.model.repository.BidRepository;
import com.example.PhaseTwo.model.repository.CommentRepository;
import com.example.PhaseTwo.model.repository.OrdersRepository;
import com.example.PhaseTwo.model.service.CommentService;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private BidRepository bidRepository;
    private OrdersRepository ordersRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BidRepository bidRepository, OrdersRepository ordersRepository) {
        this.commentRepository = commentRepository;
        this.bidRepository = bidRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Comment findById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            return comment;
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public Comment save(Comment comment) {
        Bid bid = bidRepository.findById(comment.getBid().getId()).orElse(null);
        if (bid == null) {
            throw new InputMismatchException("bad request!");
        }
        Orders orders = ordersRepository.findById(bid.getOrders().getId()).orElse(null);
        if (orders == null || orders.getStatus() != Status.Done) {
            throw new InputMismatchException("bad request!");
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.deleteById(comment.getId());
    }
}
