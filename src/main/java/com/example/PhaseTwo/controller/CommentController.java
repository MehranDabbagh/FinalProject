package com.example.PhaseTwo.controller;


import com.example.PhaseTwo.model.entity.Comment;
import com.example.PhaseTwo.model.service.impl.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> findById(@PathVariable("id") Long id) {
        Comment comment = commentService.findById(id);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        if (checkingInputObject(comment)) {
            Comment comment1 = commentService.save(comment);
            if (comment1 != null) {
                return ResponseEntity.ok(comment1);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        if (checkingInputObject(comment)) {
            commentService.update(comment);
            return ResponseEntity.ok(comment);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable("id") Long id) {
        Comment comment = commentService.findById(id);
        if (comment != null) {
            commentService.delete(comment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }


    private Boolean checkingInputObject(Comment comment) {
        if (comment.getComment() == null ||
                comment.getBid() == null ||
                comment.getScore()==null ) {
            return false;
        }
        return true;
    }
}


