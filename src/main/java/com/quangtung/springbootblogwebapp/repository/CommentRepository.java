package com.quangtung.springbootblogwebapp.repository;

import com.quangtung.springbootblogwebapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
