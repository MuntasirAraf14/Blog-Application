package com.mdmuntasirazad.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdmuntasirazad.blog.entities.Category;
import com.mdmuntasirazad.blog.entities.Post;
import com.mdmuntasirazad.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    // FIX: Updated method signature to accept Pageable and return a Page
    Page<Post> findByUser(User user, Pageable pageable);

    // FIX: Updated method signature to accept Pageable and return a Page
    Page<Post> findByCategory(Category category, Pageable pageable);

    List<Post> findByTitleContaining(String title);

}