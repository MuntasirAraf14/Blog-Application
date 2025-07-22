package com.mdmuntasirazad.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdmuntasirazad.blog.config.AppConstants;
// FIX: Removed unused and problematic import for the Post entity
import com.mdmuntasirazad.blog.payloads.ApiResponse;
import com.mdmuntasirazad.blog.payloads.PostDto;
import com.mdmuntasirazad.blog.payloads.PostResponse;
import com.mdmuntasirazad.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // POST - Create Post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    // GET - Get Posts by User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
        PostResponse postResponse = this.postService.getPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // GET - Get Posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
        PostResponse postResponse = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // GET - Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // GET - Get Single Post by ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        // FIX: The variable 'postDto' is now correctly declared as PostDto.
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // PUT - Update Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
        // FIX: The variable 'updatedPost' is now correctly declared as PostDto.
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedPost);
    }

    // DELETE - Delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }
    
    // GET - Search Posts by Title
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            // FIX: Removed the redundant ("keywords") name from the annotation.
            @PathVariable String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}