package com.example.PhaseTwo.model.service;

import com.example.PhaseTwo.model.entity.Comment;
import com.example.PhaseTwo.model.entity.Orders;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);
    Comment save(Comment comment);
    Comment update(Comment comment);
    List<Comment> findAll();
    void delete(Comment comment);
}
