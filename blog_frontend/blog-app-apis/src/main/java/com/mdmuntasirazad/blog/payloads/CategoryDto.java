package com.mdmuntasirazad.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank(message = "Category title cannot be blank!")
	@Size(min = 4, max = 100, message = "Title must be between 4 and 100 characters.")
	private String categoryTitle;
	
	@NotBlank(message = "Category description cannot be blank!")
	@Size(min = 10, message = "Description must be a minimum of 10 characters.")
	private String categoryDescription;

}