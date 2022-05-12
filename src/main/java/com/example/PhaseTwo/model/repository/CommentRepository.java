package com.example.PhaseTwo.model.repository;

import com.example.PhaseTwo.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
