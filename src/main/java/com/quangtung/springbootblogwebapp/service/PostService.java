package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto getPostById(Long id);

    void updatePost(PostDto postDto);

    void deletePost(Long id);

    PostDto findPostByUrl(String url);

    List<PostDto> searchPost(String query);
}
