package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.CommentDto;
import com.quangtung.springbootblogwebapp.entity.Comment;
import com.quangtung.springbootblogwebapp.entity.Post;
import com.quangtung.springbootblogwebapp.repository.CommentRepository;
import com.quangtung.springbootblogwebapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, String postUrl) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto responseCommentDto = modelMapper.map(savedComment,CommentDto.class);
        return responseCommentDto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDto> commentDtoList = commentList.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
        return commentDtoList;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
