package com.quangtung.springbootblogwebapp.controller;

import com.quangtung.springbootblogwebapp.dto.CommentDto;
import com.quangtung.springbootblogwebapp.dto.PostDto;
import com.quangtung.springbootblogwebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> postDtos = postService.findAllPosts();
        model.addAttribute("posts",postDtos);
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("comment",commentDto);
        return "blog/blog_post";
    }

    @GetMapping("/page/search")
    public String searchPost(@Param(value = "query") String query, Model model){
        List<PostDto> postDtos = postService.searchPost(query);
        model.addAttribute("posts",postDtos);
        return "blog/view_posts";
    }

}
