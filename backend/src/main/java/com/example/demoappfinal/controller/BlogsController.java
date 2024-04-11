package com.example.demoappfinal.controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demoappfinal.models.Blog;
import com.example.demoappfinal.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React app's origin

@RequestMapping("/api/blogs") // Sets the base path for all methods in this controller
public class BlogsController {

    @Autowired
    private BlogService blogService;

    @GetMapping // Maps to the base path, i.e., '/api/blogs'
    public ResponseEntity<Map<String, Blog>> getAllBlogs() {
        Map<String, Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs); // Returns the blogs as JSON
    }
}
