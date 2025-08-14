package com.mdmuntasirazad.blog.controllers;

import com.mdmuntasirazad.blog.payloads.PostDto;
import com.mdmuntasirazad.blog.payloads.PostResponse;
import com.mdmuntasirazad.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

// This annotation secures all methods in this controller to only be accessible by users with the 'ADMIN' role.
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private PostService postService;

    // GET all pending posts for review (paginated)
    @GetMapping("/posts/pending")
    public ResponseEntity<PostResponse> getPendingPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        PostResponse pendingPosts = this.postService.getPendingPosts(pageNumber, pageSize);
        return ResponseEntity.ok(pendingPosts);
    }

    // PUT to approve a post
    @PutMapping("/posts/{postId}/approve")
    public ResponseEntity<PostDto> approvePost(@PathVariable Integer postId) {
        PostDto approvedPost = this.postService.approvePost(postId);
        return ResponseEntity.ok(approvedPost);
    }

    // PUT to reject a post
    @PutMapping("/posts/{postId}/reject")
    public ResponseEntity<PostDto> rejectPost(@PathVariable Integer postId) {
        PostDto rejectedPost = this.postService.rejectPost(postId);
        return ResponseEntity.ok(rejectedPost);
    }
}