package com.mdmuntasirazad.blog.entities;

import jakarta.persistence.*;
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

    @ManyToOne
    private Post post;

    // --- THIS IS THE NEW RELATIONSHIP ---
    @ManyToOne
    @JoinColumn(name = "user_id") // This will create a user_id foreign key column
    private User user;
    // ------------------------------------
}