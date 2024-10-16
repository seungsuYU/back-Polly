package com.sparta.spring_prepare.api;

import com.sparta.spring_prepare.Entity.PostList;
import com.sparta.spring_prepare.Service.PostListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostListController {

    @Autowired
    private PostListService postListService;

    @GetMapping("/All")
    public String getAllPosts(Model model) {
        List<PostList> posts = postListService.findAll();
        model.addAttribute("posts", posts);
        return "post_list";
    }


    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new PostList());
        return "create_post";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostList post) {
        postListService.save(post);
        return "redirect:/post";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Optional<PostList> postOptional = postListService.findById(id);
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "edit_post";  // edit_post.html을 렌더링
        } else {
            return "redirect:/post";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostList postDetails) {
        PostList post = postListService.findById(id).orElseThrow();
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setAuthor(postDetails.getAuthor());
        postListService.save(post);
        return "redirect:/post";
    }
}
