package com.example.demo.controller.Blog;

import java.util.List;

import com.example.demo.model.Blog.Blog;
import com.example.demo.service.Blog.BlogService;
import com.example.demo.util.ErrrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/get")
    public ResponseEntity<?> getAllBlogs() {
        try{
            List<Blog> listBlogFromDB = blogService.getAllBlogs();
            return ResponseEntity.ok(listBlogFromDB);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "cannot get data from server"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
        try{
            Blog newBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(newBlog);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "Internal Server Error"));
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> findBlogById(@PathVariable String id) {
        try{
            Blog blog = blogService.findBlogById(id);
            return ResponseEntity.ok(blog);
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrrorException(404, "Post is not existed"));
        }
    }
    @PutMapping("/change/{id}")
    public ResponseEntity<?> changeBlogById(@PathVariable String id, @RequestBody Blog blog) {
        try{
            Blog newBlogAfterUpdate = blogService.changeBlogById(id, blog);
            return ResponseEntity.ok(newBlogAfterUpdate);
        }catch(Exception err){
            System.out.println(err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "Internal Server Error"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBlogById(@RequestBody String id){
        try{
            blogService.deleteBlogById(id);
            return ResponseEntity.ok().body(new ErrrorException(200, "successfully"));
        }catch(Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrrorException(500, "Cannot delete blog"));
        }
    }



}
