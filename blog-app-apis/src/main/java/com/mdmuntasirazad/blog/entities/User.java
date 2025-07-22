package com.mdmuntasirazad.blog.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Changed from AUTO to IDENTITY for better predictability.
    private int id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true) // Email should be non-nullable and unique.
    private String email;

    @Column(nullable = false, length = 255) // Password should be non-nullable.
    private String password;

    @Column(length = 500) // Good practice to set a length for the 'about' field.
    private String about;
    
    // --- Relationship ---
    // A user can have many posts. This is the other side of the Post-User relationship.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("post-user") // Manages serialization to prevent infinite loops.
    private List<Post> posts = new ArrayList<>();
}