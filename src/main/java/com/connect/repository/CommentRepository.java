package com.connect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	
	
}
