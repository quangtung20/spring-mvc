package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, String postUrl);

    List<CommentDto> getAllComments();

    void deleteComment(Long commentId);
}
