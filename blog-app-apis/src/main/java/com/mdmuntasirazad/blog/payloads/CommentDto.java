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

	// This allows us to see who made the comment in the API response.
	private UserDto user;
}