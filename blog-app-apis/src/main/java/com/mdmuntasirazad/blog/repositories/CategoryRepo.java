package com.mdmuntasirazad.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdmuntasirazad.blog.entities.Category;

@Repository // Marks this interface as a Spring Data repository.
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	// Spring Data JPA will automatically provide all the basic CRUD methods:
	// - save()
	// - findById()
	// - findAll()
	// - deleteById()
	// ... and many more, including pagination and sorting.
	
	// You can also add custom finder methods here if needed.
	// For example, to find a category by its title:
	// Optional<Category> findByCategoryTitle(String categoryTitle);
	
}