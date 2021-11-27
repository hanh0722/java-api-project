package com.example.demo.service.Blog;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.Blog.Blog;
import com.example.demo.repository.BlogRepository.BlogRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Blog createBlog(Blog blog) {
        blog.setTime_created(System.currentTimeMillis());
        return blogRepository.insert(blog);
    }

    public Blog findBlogById(String id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("post is not existed"));
    }

    public Blog changeBlogById(String id, Blog blog) {
        Blog findBlog = findBlogById(id);
        findBlog.setTitle(blog.getTitle());
        findBlog.setShort_description(blog.getShort_description());
        findBlog.setCategory(blog.getCategory());
        findBlog.setContent(blog.getContent());
        findBlog.setCover_image(blog.getCover_image());
        findBlog.setIs_public(blog.getIs_public());
        Blog blogAfterUpdate = blogRepository.save(findBlog);
        return blogAfterUpdate;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void deleteBlogById(String id) {
        Blog blog = findBlogById(id);
        blogRepository.delete(blog);
    }
}
