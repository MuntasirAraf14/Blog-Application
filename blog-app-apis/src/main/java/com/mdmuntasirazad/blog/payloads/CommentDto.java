package com.mdmuntasirazad.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	private int id;
	
	private String content;

	// Optional: It can be useful to include who made the comment.
	// We use UserDto to avoid exposing the full User entity.
	private UserDto user;
}