package com.example.demo.repository.BlogRepository;

import java.util.Optional;

import com.example.demo.model.Blog.Blog;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlogRepository extends MongoRepository<Blog, String> {
}
