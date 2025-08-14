package com.mdmuntasirazad.blog.payloads;

import com.mdmuntasirazad.blog.entities.PostStatus; // <-- Import the new enum
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    @NotBlank(message = "Post title is required.")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters.")
    private String title;

    @NotBlank(message = "Post content cannot be empty.")
    @Size(min = 20, message = "Content must be at least 20 characters long.")
    private String content;

    private String imageName;

    private Date addedDate;

    // --- THIS IS THE NEW FIELD ---
    private PostStatus status;
    // ----------------------------

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}