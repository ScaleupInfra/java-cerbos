package com.example.demoappfinal.controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demoappfinal.models.Blog;
import com.example.demoappfinal.data.DataLoader;
import com.example.demoappfinal.services.BlogService;
import dev.cerbos.sdk.CerbosBlockingClient;
import dev.cerbos.sdk.CheckResult;
import dev.cerbos.sdk.builders.AttributeValue;
import dev.cerbos.sdk.builders.Principal;
import dev.cerbos.sdk.builders.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // React app's origin

@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;
    private final CerbosBlockingClient cerbosClient;

    @Autowired
    public BlogController(BlogService blogService, CerbosBlockingClient cerbosClient) {
        this.blogService = blogService;
        this.cerbosClient = cerbosClient;
    }

    @GetMapping
    public ResponseEntity<?> getBlog(@RequestParam String blogId, @RequestParam String userId) {
        try {
            Blog blog = blogService.getBlog(blogId);
            String role = DataLoader.users.get(userId).getRole();
            String own = DataLoader.users.get(userId).getName();
            String blogOwner = blog.getOwner();

            Principal principal = Principal.newInstance(userId, role)
                .withAttribute("id", AttributeValue.stringValue(own));

            Resource resource = Resource.newInstance("blog", blogId)
                .withAttribute("owner", AttributeValue.stringValue(blogOwner));


            CheckResult result = cerbosClient.check(
                principal,
                resource,
                "read");

            if (!result.isAllowed("read")) {
                return ResponseEntity.status(403).body("Forbidden");
            }

            return ResponseEntity.ok(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error processing request: " + e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateBlog(@RequestParam String blogId, @RequestBody Blog blogUpdates, @RequestParam String userId) {
        try {
            Blog blog = blogService.getBlog(blogId);
            String role = DataLoader.users.get(userId).getRole();
            String own = DataLoader.users.get(userId).getName();
            String blogOwner = blog.getOwner();
System.out.println(blogId);
System.out.println(userId);

            Principal principal = Principal.newInstance(userId, role)
                .withAttribute("id", AttributeValue.stringValue(own));

            Resource resource = Resource.newInstance("blog", blogId)
                .withAttribute("owner", AttributeValue.stringValue(blogOwner));


            CheckResult result = cerbosClient.check(
                principal,
                resource,
                "update");

            if (!result.isAllowed("update")) {
                return ResponseEntity.status(403).body("Forbidden");
            }

            Blog updatedBlog = blogService.updateBlog(blogId, blogUpdates);
            return ResponseEntity.ok(updatedBlog);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error processing request: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBlog(@RequestParam String blogId, @RequestParam String userId) {
        try {
            Blog blog = blogService.getBlog(blogId);
            String role = DataLoader.users.get(userId).getRole();
            String own = DataLoader.users.get(userId).getName();
            String blogOwner = blog.getOwner();

            Principal principal = Principal.newInstance(userId, role)
                .withAttribute("id", AttributeValue.stringValue(own));

            Resource resource = Resource.newInstance("blog", blogId)
                .withAttribute("owner", AttributeValue.stringValue(blogOwner));


            CheckResult result = cerbosClient.check(
                principal,
                resource,
                "delete");

            if (!result.isAllowed("delete")) {
                return ResponseEntity.status(403).body("Forbidden");
            }

            boolean deleted = blogService.deleteBlog(blogId);
            if (deleted) {
                return ResponseEntity.ok().body("Blog deleted successfully");
            } else {
                return ResponseEntity.internalServerError().body("Failed to delete blog");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error processing request: " + e.getMessage());
        }
    }
}
