package alim.project.blogapp.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "authors")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable=false)
    private String name;
    private String username;
    @Column(nullable=false)
    private String password;
    private String authority;


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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String role) {
        this.authority = role;
    }
    public UserDetails orElseThrow(Object userNotFound) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
