package com.mdmuntasirazad.blog.services;

import java.util.List;
import com.mdmuntasirazad.blog.payloads.CategoryDto;

public interface CategoryRepo {
	
	// Create a new category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// Update an existing category
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	// Delete a category
	void deleteCategory(Integer categoryId);
	
	// Get a single category by its ID
	CategoryDto getCategory(Integer categoryId);
	
	// Get a list of all categories
	List<CategoryDto> getAllCategories();

}