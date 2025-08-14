package com.mdmuntasirazad.blog.services;

import com.mdmuntasirazad.blog.payloads.CommentDto;

public interface CommentService {

    /**
     * Creates a new comment for a post, authored by the logged-in user.
     * @param commentDto The DTO containing the comment's content.
     * @param postId The ID of the post to which this comment belongs.
     * @param username The email of the logged-in user who is commenting.
     * @return The created comment as a DTO.
     */
    CommentDto createComment(CommentDto commentDto, Integer postId, String username);

    /**
     * Deletes a comment. Authorization should check if the requester is the comment's author or an admin.
     * @param commentId The ID of the comment to be deleted.
     */
    void deleteComment(Integer commentId);
}