package com.mdmuntasirazad.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    // A comment must belong to a post. This is the "owning" side.
    @ManyToOne
    @JoinColumn(name = "post_id") // Creates the foreign key
    @JsonBackReference("post-comment") // Prevents infinite loop during serialization
    private Post post;
}