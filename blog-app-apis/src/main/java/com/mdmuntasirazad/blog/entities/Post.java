package com.mdmuntasirazad.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(length = 10000) // Use a larger length for the post content
    private String content;

    private String imageName;

    private Date addedDate;

    // --- Relationships ---

    // Many posts can belong to one category. This is the "owning" side of the relationship.
    @ManyToOne
    @JoinColumn(name = "category_id") // Creates the foreign key column 'category_id' in the 'posts' table.
    @JsonBackReference // This is crucial. It prevents infinite recursion during JSON serialization.
    private Category category;

    // Many posts can be written by one user.
    @ManyToOne
    @JoinColumn(name = "user_id") // Creates the foreign key column 'user_id' in the 'posts' table.
    private User user;

    // A post can have many comments.
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}