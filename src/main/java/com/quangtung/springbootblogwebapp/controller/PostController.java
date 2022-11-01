package com.quangtung.springbootblogwebapp.controller;

import com.quangtung.springbootblogwebapp.dto.CommentDto;
import com.quangtung.springbootblogwebapp.dto.PostDto;
import com.quangtung.springbootblogwebapp.service.CommentService;
import com.quangtung.springbootblogwebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts",posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPost(Model model){
        PostDto newPost = new PostDto();
        model.addAttribute("post",newPost);
        return "admin/newpost";
    }

    @PostMapping("/admin/posts")
    public String createPost(
            @Valid @ModelAttribute("post") PostDto postDto,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return "admin/newpost";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("admin/posts/{postId}/edit")
    public String editPost(@PathVariable("postId") Long postId,Model model){
        PostDto postDto = postService.getPostById(postId);
        model.addAttribute("post",postDto);
        return "admin/edit_post";
    }

    @PostMapping("admin/posts/{postId}")
    public String updatePost(
            @PathVariable("postId") Long postId,
            @ModelAttribute("post") PostDto postDto,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("post",postDto);
            return "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPost(@RequestParam(value = "query") String query, Model model){
        List<PostDto> posts = postService.searchPost(query);
        model.addAttribute("posts",posts);
        return "admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String allComments(Model model){
        List<CommentDto> commentDtoList = commentService.getAllComments();
        model.addAttribute("comments",commentDtoList);
        return "admin/comments";
    }

    @GetMapping("/admin/comments/{commentId}/delete")
    public String deleteComment(Model model, @PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        List<CommentDto> commentDtoList = commentService.getAllComments();
        model.addAttribute("comments",commentDtoList);
        return "admin/comments";
    }
    private static String getUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+","-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }
}
