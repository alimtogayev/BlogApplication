package alim.project.blogapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Comment> comments;

    public void setPost(List<Post> posts) {
        this.posts = posts;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        return posts;
    }

    public User() {
        // Default constructor
    }

    public List<Like> getLikes() {
        if (likes == null) {
            likes = new ArrayList<>();
        }
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
