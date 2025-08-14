package com.mdmuntasirazad.blog.services;

import com.mdmuntasirazad.blog.payloads.PostDto;
import com.mdmuntasirazad.blog.payloads.PostResponse;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, String username, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);
    List<PostDto> getPostsByUsername(String username);
    PostResponse getPendingPosts(Integer pageNumber, Integer pageSize);
    PostDto approvePost(Integer postId);
    PostDto rejectPost(Integer postId);
    
    // The required method signature
    PostDto updatePostImage(Integer postId, String imageName);
}