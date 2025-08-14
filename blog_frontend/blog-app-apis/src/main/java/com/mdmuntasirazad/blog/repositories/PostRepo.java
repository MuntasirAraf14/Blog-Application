package com.mdmuntasirazad.blog.repositories;

import com.mdmuntasirazad.blog.entities.Category;
import com.mdmuntasirazad.blog.entities.Post;
import com.mdmuntasirazad.blog.entities.PostStatus; // <-- Import the enum
import com.mdmuntasirazad.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    // --- OLD METHODS (We will replace their usage) ---
    // List<Post> findByUser(User user);
    // List<Post> findByCategory(Category category);
    // These are still okay, but the new methods below are more specific.

    // --- NEW METHODS FOR THE APPROVAL WORKFLOW ---

    /**
     * Finds all posts by a specific user with a given status.
     * Used for showing only APPROVED posts on a user's public profile.
     */
    List<Post> findByUserAndStatus(User user, PostStatus status);

    /**
     * Finds all posts in a specific category with a given status.
     * Used for showing only APPROVED posts on a category page.
     */
    List<Post> findByCategoryAndStatus(Category category, PostStatus status);

    /**
     * Finds all posts with a given status, with pagination.
     * This will be used for:
     * 1. The main public feed (status=APPROVED).
     * 2. The admin's dashboard of pending posts (status=PENDING).
     */
    Page<Post> findByStatus(PostStatus status, Pageable pageable);

    List<Post> findByUserOrderByAddedDateDesc(User user);
    // --- YOUR EXISTING SEARCH METHOD (This is still perfect) ---
    /**
     * Searches for posts where the title contains the given keywords.
     * Note: This will search ALL posts, including PENDING ones. We can refine this later if needed.
     */
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);
}