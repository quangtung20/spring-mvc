package com.quangtung.springbootblogwebapp.repository;

import com.quangtung.springbootblogwebapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByUrl(String url);

    List<Post> findByTitleContaining(String query);

}
