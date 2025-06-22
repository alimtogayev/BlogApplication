package alim.project.blogapp.controller;

import alim.project.blogapp.dto.*;
import alim.project.blogapp.entities.Post;
import alim.project.blogapp.entities.User;
import alim.project.blogapp.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class        BlogController {
    private final BlogService blogService;


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/users")
    public UserResponse createUser(@RequestBody User user) {
        return blogService.addUser(user);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return blogService.getAllUsers();
    }

    @PostMapping("/users/{id}/posts")
    public PostResponse addPost(@PathVariable(name = "id") Long id, @RequestBody PostInfo postInfo) {
        return blogService.addPostToUser(id, postInfo.getTitleInfo(), postInfo.getContentInfo());
    }

    @GetMapping("/users/{id}/posts")
    public List<PostResponse> getUserPosts(@PathVariable(name = "id") Long id) {
        return blogService.showPostsByUser(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getPosts() {
        return blogService.showAllPosts();
    }

    @PostMapping("users/{uid}/posts/{pid}/comments")
    public CommentResponse commentPost(@PathVariable(name = "uid") Long uid, @PathVariable(name = "pid") Long pid, @RequestBody CommentBody commentBody) {
        return blogService.addComment(commentBody.getContent(), pid, uid);
    }

    @GetMapping("/posts/{id}/comments")
    public List<CommentResponse> getPostComments(@PathVariable(name = "id") Long id) {
        return blogService.showPostComments(id);
    }

    @PostMapping("/users/{uid}/posts/{pid}")
    public LikeResponse likePost(@PathVariable(name = "uid") Long uid, @PathVariable(name = "pid") Long pid) {
        return blogService.addLike(pid, uid);
    }

    @GetMapping("/users/{id}/likes")
    public List<LikeResponse> getUserLikes(@PathVariable(name = "id") Long id) {
        return blogService.showUserLikes(id);
    }

    @GetMapping("/posts/{id}/likes")
    public List<LikeResponse> getPostLikes(@PathVariable(name = "id") Long id) {
        return blogService.showPostLikes(id);
    }

    @GetMapping("/users/{id}/comments")
    public List<CommentResponse> getUserComments(@PathVariable(name = "id") Long id) {
        return blogService.showUserComments(id);
    }
}