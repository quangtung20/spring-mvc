package com.quangtung.springbootblogwebapp.controller;

import com.quangtung.springbootblogwebapp.dto.CommentDto;
import com.quangtung.springbootblogwebapp.dto.PostDto;
import com.quangtung.springbootblogwebapp.service.CommentService;
import com.quangtung.springbootblogwebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping("/{postUrl}/comments")
    public String createComment(
            @PathVariable("postUrl") String postUrl,
            @Valid @ModelAttribute("comment")CommentDto commentDto,
            BindingResult result,
            Model model
    ){
        PostDto postDto = postService.findPostByUrl(postUrl);
        System.out.println(postDto);
        if(result.hasErrors()){
            model.addAttribute("comment",commentDto);
            model.addAttribute("post",postDto);
            return "blog/blog_post";
        }
        commentService.createComment(commentDto, postUrl);
        return "redirect:/post/"+postUrl;
    }
}
