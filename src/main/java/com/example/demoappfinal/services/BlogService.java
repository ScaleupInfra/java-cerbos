package com.example.demoappfinal.services;

import com.example.demoappfinal.data.DataLoader;
import com.example.demoappfinal.models.Blog;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class BlogService {

    // Reference the DataLoader's blogs map
    private final Map<String, Blog> blogs = DataLoader.blogs;

     public Blog getBlog(String id) {
        return blogs.get(id);
    }

    public Blog updateBlog(String id, Blog updateData) {
        Blog existingBlog = blogs.get(id);
        if (existingBlog == null) {
            return null;
        }
        if (updateData.getTitle() != null) {
            existingBlog.setTitle(updateData.getTitle());
        }
        if (updateData.getContent() != null) {
            existingBlog.setContent(updateData.getContent());
        }
        // Update other fields as needed
        blogs.put(id, existingBlog);
        return existingBlog;
    }

    public boolean deleteBlog(String id) {
        if (blogs.containsKey(id)) {
            blogs.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
