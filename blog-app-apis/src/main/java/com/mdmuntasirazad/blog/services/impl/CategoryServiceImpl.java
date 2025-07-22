package com.mdmuntasirazad.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdmuntasirazad.blog.entities.Category;
import com.mdmuntasirazad.blog.exceptions.ResourceNotFoundException;
import com.mdmuntasirazad.blog.payloads.CategoryDto;
import com.mdmuntasirazad.blog.repositories.CategoryRepo;
import com.mdmuntasirazad.blog.services.CategoryService;

@Service // Marks this as a Spring service component.
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private ModelMapper modelMapper; // Used for easy object mapping.

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // Convert DTO to Entity
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        // Save the entity to the database
        Category addedCat = this.categoryRepo.save(cat);
        // Convert the saved entity back to DTO to return to the client
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        // First, find the category by its ID. If not found, throw an exception.
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        
        // Update the fields from the incoming DTO
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        // Save the updated category
        Category updatedCat = this.categoryRepo.save(cat);

        // Convert the updated entity to DTO and return
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // Find the category to delete. Throws an exception if it doesn't exist.
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        // Find the category by its ID. Throws an exception if it doesn't exist.
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        // Fetch all categories from the database
        List<Category> categories = this.categoryRepo.findAll();
        
        // Use a Java Stream to map the list of entities to a list of DTOs
        List<CategoryDto> categoryDtos = categories.stream()
                .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
        
        return categoryDtos;
    }
}