package com.mdmuntasirazad.blog.entities;

import jakarta.persistence.*; // Make sure all imports are from jakarta
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(length = 10000) // Note: A very large length might be better handled by @Lob or columnDefinition="TEXT"
    private String content;

    private String imageName;

    private Date addedDate;

    // --- THIS IS THE NEW FIELD FOR THE APPROVAL WORKFLOW ---
    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private PostStatus status;
    // --------------------------------------------------------

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();
}