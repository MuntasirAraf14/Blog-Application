package com.mdmuntasirazad.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	@NotBlank
	@Size(min = 5, message = "Post title must be at least 5 characters long.")
	private String title;
	
	@NotBlank
	@Size(min = 10, message = "Post content must be at least 10 characters long.")
	private String content;
	
	// We will send the image name as a string, not the full file.
	private String imageName;
	
	private Date addedDate;
	
	// For relationships, we include the DTO of the related entity, not the entity itself.
	// This prevents exposing internal entity details and avoids serialization issues.
	private CategoryDto category;
	
	private UserDto user;
	
	// It's also good practice to include comments if you want to display them with the post.
	// Note: You would need to create a CommentDto class for this.
	private Set<CommentDto> comments = new HashSet<>();
}