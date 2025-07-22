package com.mdmuntasirazad.blog.entities;

import java.util.ArrayList;
import java.util.List;

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



@Entity // Marks this class as a JPA entity, so it can be mapped to a database table.
@Table(name = "categories") // Specifies the name of the database table.
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way of incrementing the primary key. IDENTITY is best for MySQL.
    private Integer categoryId;

    @Column(name = "title", length = 100, nullable = false) // Defines column properties.
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;

    // A category can have many posts. This establishes the one-to-many relationship.
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

}