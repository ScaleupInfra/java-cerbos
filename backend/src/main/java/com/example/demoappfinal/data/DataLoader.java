package com.example.demoappfinal.data;

import java.util.HashMap;
import java.util.Map;

import com.example.demoappfinal.models.Blog;
import com.example.demoappfinal.models.User;

public class DataLoader {

    public static final Map<String, User> users = new HashMap<>();
    public static final Map<String, Blog> blogs = new HashMap<>();

    static {
        // Initialize users
        users.put("1", new User("1", "user1", "user"));
        users.put("2", new User("2", "user2", "admin"));
        users.put("3", new User("3", "user3", "user"));
        users.put("4", new User("4", "user4", "user"));
        users.put("5", new User("5", "user5", "admin"));

        // Initialize blogs
        blogs.put("1", new Blog("1", "First Blog", "This is an amazing blog.", "user1"));
        blogs.put("2", new Blog("2", "Second Blog", "Discover the secrets of the universe.", "user2"));
        blogs.put("3", new Blog("3", "Third Blog", "Exploring the depths of the ocean.", "user3"));
        blogs.put("4", new Blog("4", "Fourth Blog", "Understanding the mysteries of the forest.", "user4"));
        blogs.put("5", new Blog("5", "Fifth Blog", "The journey through the mountains.", "user5"));
    }
}
