package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Comment;
import com.example.PhaseTwo.model.repository.CommentRepository;
import com.example.PhaseTwo.model.service.CommentService;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findById(Long id) {
        Comment comment=commentRepository.findById(id).orElse(null);
        if(comment!=null) {
            return comment;
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment){
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
