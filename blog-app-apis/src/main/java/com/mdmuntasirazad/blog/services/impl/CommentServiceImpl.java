package com.mdmuntasirazad.blog.services.impl;

import com.mdmuntasirazad.blog.entities.Comment;
import com.mdmuntasirazad.blog.entities.Post;
import com.mdmuntasirazad.blog.entities.User;
import com.mdmuntasirazad.blog.exceptions.ResourceNotFoundException;
import com.mdmuntasirazad.blog.payloads.CommentDto;
import com.mdmuntasirazad.blog.repositories.CommentRepo;
import com.mdmuntasirazad.blog.repositories.PostRepo;
import com.mdmuntasirazad.blog.repositories.UserRepo;
import com.mdmuntasirazad.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, String username) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", username));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user); // Link the comment to the user

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        // NOTE: Add authorization check here (is user the author or an admin?)
        Comment com = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        this.commentRepo.delete(com);
    }
}