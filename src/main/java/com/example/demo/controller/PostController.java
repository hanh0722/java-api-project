package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/findAllPost")
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
    @GetMapping("/findAllPost/{id}")
    public Optional<Post> getPostById(@PathVariable String id){
        return postRepository.findById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable String id){
        postRepository.deleteById(id);
        return "Post deleted with id: "+id;
    }
    @DeleteMapping("/deleteAllPost")
    public String deletePost(){
        postRepository.deleteAll();
        return "Post deleted all";
    }
    @PostMapping("/addPost")
    public String savePost(@RequestBody Post post){
        postRepository.save(post);

        return "Add Post success";
    }
    @PutMapping("/putPost/{id}")
    public String putPost(@PathVariable String id,@RequestBody Post post){
        Post oldPost = postRepository.findById(id).orElse(null);
        oldPost.setTitle(post.getTitle());
        oldPost.setDes(post.getDes());
        oldPost.setContent(post.getContent());
        oldPost.setUrl(post.getUrl());
        oldPost.setPublish(post.isPublish());
        postRepository.save(oldPost);
        return "Put success";
    }
}
