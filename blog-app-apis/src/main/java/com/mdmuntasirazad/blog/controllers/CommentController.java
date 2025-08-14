package com.mdmuntasirazad.blog.controllers;

import com.mdmuntasirazad.blog.payloads.ApiResponse;
import com.mdmuntasirazad.blog.payloads.CommentDto;
import com.mdmuntasirazad.blog.services.CommentService;
import jakarta.validation.Valid; // Import for validation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal; // Import the Principal

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // FIX: Updated createComment method
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable Integer postId,
            Principal principal // 1. Get the logged-in user securely
    ) {
        // 2. Pass the username to the service layer
        CommentDto createComment = this.commentService.createComment(commentDto, postId, principal.getName());
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        // NOTE: Authorization (checking if the user is the author or an admin)
        // will be handled inside the service implementation.
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
    }
}