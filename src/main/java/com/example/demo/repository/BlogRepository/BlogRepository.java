package com.example.demo.repository.BlogRepository;

import com.example.demo.model.Blog.Blog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {
    
}
