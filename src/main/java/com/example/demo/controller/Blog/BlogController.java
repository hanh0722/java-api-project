package com.example.demo.controller.Blog;

import java.util.List;

import com.example.demo.model.Blog.Blog;
import com.example.demo.service.Blog.BlogService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/blog")
public class BlogController {
    
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> listBlogFromDB = blogService.getAllBlogs();
        return ResponseEntity.ok(listBlogFromDB);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog newBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(newBlog);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable String id) {
        Blog blog = blogService.findBlogById(id);
        return ResponseEntity.ok(blog);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Blog> changeBlogById(@PathVariable String id, @RequestBody Blog blog) {
        Blog newBlogAfterUpdate = blogService.changeBlogById(id, blog);
        return ResponseEntity.ok(newBlogAfterUpdate);
    }

}
