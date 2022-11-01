package com.quangtung.springbootblogwebapp.service;

import com.quangtung.springbootblogwebapp.dto.PostDto;
import com.quangtung.springbootblogwebapp.entity.Post;
import com.quangtung.springbootblogwebapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto,Post.class);
        postRepository.save(post);
    }

    @Override
    public PostDto getPostById(Long id) {
        PostDto postDto = modelMapper.map(postRepository.findById(id).get(),PostDto.class);
        return postDto;
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = modelMapper.map(postDto,Post.class);
        postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDto findPostByUrl(String url) {
        PostDto postDto = modelMapper.map(postRepository.findByUrl(url).get(),PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> searchPost(String query) {
        List<Post> posts = postRepository.findByTitleContaining(query);
        List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

}
