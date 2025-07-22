package com.mdmuntasirazad.blog.services;

import java.util.List;
// FIX: Removed unused import for Post entity
import com.mdmuntasirazad.blog.payloads.PostDto;
import com.mdmuntasirazad.blog.payloads.PostResponse;

public interface PostService {
	
	/**
	 * Creates a new post for a given user and category.
	 * @param postDto The DTO containing the post data.
	 * @param userId The ID of the user creating the post.
	 * @param categoryId The ID of the category this post belongs to.
	 * @return The created post as a DTO.
	 */
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	/**
	 * Updates an existing post.
	 * @param postDto The DTO with the updated post data.
	 * @param postId The ID of the post to update.
	 * @return The updated post as a DTO.
	 */
	// FIX: Changed return type from Post to PostDto
	PostDto updatePost(PostDto postDto, Integer postId);
	
	/**
	 * Deletes a post by its ID.
	 * @param postId The ID of the post to delete.
	 */
	void deletePost(Integer postId);
	
	/**
	 * Retrieves all posts with pagination and sorting.
	 * @param pageNumber The page number to retrieve (starts from 0).
	 * @param pageSize The number of posts per page.
	 * @param sortBy The field to sort the posts by.
	 * @param sortDir The direction of sorting ('asc' or 'desc').
	 * @return A PostResponse object containing the list of posts and pagination details.
	 */
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	/**
	 * Retrieves a single post by its ID.
	 * @param postId The ID of the post to retrieve.
	 * @return The found post as a DTO.
	 */
	// FIX: Changed return type from Post to PostDto
	PostDto getPostById(Integer postId);
	
	// FIX: Removed the redundant and problematic getAllPost() method.
	
	/**
	 * Retrieves all posts belonging to a specific category, with pagination.
	 * @param categoryId The ID of the category.
	 * @param pageNumber The page number to retrieve.
	 * @param pageSize The number of posts per page.
	 * @return A PostResponse object containing the list of posts and pagination details.
	 */
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
	
	/**
	 * Retrieves all posts written by a specific user, with pagination.
	 * @param userId The ID of the user.
	 * @param pageNumber The page number to retrieve.
	 * @param pageSize The number of posts per page.
	 * @return A PostResponse object containing the list of posts and pagination details.
	 */
	PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);
	
	/**
	 * Searches for posts by a keyword in their title.
	 * @param keyword The search term.
	 * @return A list of posts whose titles contain the keyword.
	 */
	List<PostDto> searchPosts(String keyword);

}