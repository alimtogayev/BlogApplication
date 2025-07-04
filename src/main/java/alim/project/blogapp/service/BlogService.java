package alim.project.blogapp.service;

import alim.project.blogapp.dto.*;
import alim.project.blogapp.entities.Comment;
import alim.project.blogapp.entities.Like;
import alim.project.blogapp.entities.Post;
import alim.project.blogapp.entities.User;
import alim.project.blogapp.repo.CommentRepository;
import alim.project.blogapp.repo.LikeRepository;
import alim.project.blogapp.repo.PostRepository;
import alim.project.blogapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    private final UserRepository userRepo;
    private final PostRepository postRepo;
    private final LikeRepository likeRepo;
    private final CommentRepository commentRepo;
    private AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public BlogService(UserRepository userRepo, PostRepository postRepo, LikeRepository likeRepo,
                       CommentRepository commentRepo,
                       PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.likeRepo = likeRepo; // Assuming likeRepo is not used in this service, otherwise initialize it
        this.commentRepo = commentRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setName(user.getUsername());
            List<Post> postList = getPostsByUser(user.getId());
            List<Long> postIds = new ArrayList<>();
            for (Post post : postList) {
                postIds.add(post.getId());
            }
            userResponse.setPostList(postIds);
            List<Comment> commentList = getCommentsByUser(user.getId());
            List<Long> commentIds = new ArrayList<>();
            for (Comment comment : commentList) {
                commentIds.add(comment.getId());
            }
            userResponse.setCommentList(commentIds);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public List<PostResponse> showPostsByUser(Long userId) {
        List<Post> postList = postRepo.findByUserId(userId);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : postList) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setUserId(post.getUser().getId());
            postResponses.add(postResponse);
        }
        return postResponses;
    }

    public List<Post> getPostsByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return postRepo.findByUserId(user.getId());
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public UserResponse addUser(User user) {
        user.setId(null);
        userRepo.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setCommentList(null);
        userResponse.setPostList(null);
        userResponse.setLikeList(null);
        return userResponse;
    }

    public PostResponse addPostToUser(Long userId, String titleInfo, String contentInfo) {
        Post post = new Post();
        User user = userRepo.findById(userId).orElseThrow();
        post.setUser(user);
        post.setContent(contentInfo);
        post.setTitle(titleInfo);
        postRepo.save(post);
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setUserId(post.getUser().getId());
        user.getPosts().add(post);
        userRepo.save(user);
        return postResponse;
    }

    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    public LikeResponse addLike(Long postId, Long userId) {
        Like like = new Like();
        Post post = postRepo.findById(postId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();
        like.setPost(post);
        like.setUser(user);
        post.getLikes().add(like);
        user.getLikes().add(like);
        likeRepo.save(like);
        LikeResponse likeResponse = new LikeResponse();
        likeResponse.setId(like.getId());
        likeResponse.setUserId(like.getUser().getId());
        likeResponse.setPostId(like.getPost().getId());
        return likeResponse;
    }

    public CommentResponse addComment(String content, Long postId, Long userId) {
        Comment comment = new Comment();
        Post post = postRepo.findById(postId).orElseThrow();
        User user = userRepo.findById(userId).orElseThrow();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);
        commentRepo.save(comment);
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setUserId(comment.getUser().getId());
        commentResponse.setPostId(comment.getPost().getId());
        post.getComments().add(comment);
        postRepo.save(post);
        user.getComments().add(comment);
        userRepo.save(user);
        return commentResponse;
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    public List<Like> getLikeByPost(Long postId) {
        return likeRepo.findByPostId(postId);
    }

    public List<Like> getLikesByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return likeRepo.findByUserId(user.getId());
    }

    public List<Comment> getCommentsByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return commentRepo.findByUserId(user.getId());
    }

    public List<PostResponse> showUserPosts(Long userId) {
        List<Post> posts = postRepo.findByUserId(userId);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setUserId(post.getUser().getId());
            postResponses.add(postResponse);
        }
        return postResponses;
    }

    public List<PostResponse> showAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setUserId(post.getUser().getId());
            postResponses.add(postResponse);
            List<Long> likesList = new ArrayList<>();
            for (Like like : post.getLikes()) {
                likesList.add(like.getId());
            }
            postResponse.setLikesList(likesList);
            List<Long> commentsList = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                commentsList.add(comment.getId());
            }
            postResponse.setCommentsList(commentsList);
        }
        return postResponses;
    }

    public List<CommentResponse> showPostComments(Long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            commentResponse.setUserId(comment.getUser().getId());
            commentResponse.setPostId(comment.getPost().getId());
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }

    public List<CommentResponse> showUserComments(Long userId) {
        List<Comment> comments = commentRepo.findByUserId(userId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            commentResponse.setUserId(comment.getUser().getId());
            commentResponse.setPostId(comment.getPost().getId());
            commentResponses.add(commentResponse);
        }
        return commentResponses;
    }

    public List<LikeResponse> showPostLikes(Long postId) {
        List<Like> likes = likeRepo.findByPostId(postId);
        List<LikeResponse> likeResponses = new ArrayList<>();
        for (Like like : likes) {
            LikeResponse likeResponse = new LikeResponse();
            likeResponse.setId(like.getId());
            likeResponse.setUserId(like.getUser().getId());
            likeResponse.setPostId(like.getPost().getId());
            likeResponses.add(likeResponse);
        }
        return likeResponses;
    }

    public List<LikeResponse> showUserLikes(Long userId) {
        List<Like> likes = likeRepo.findByUserId(userId);
        List<LikeResponse> likeResponses = new ArrayList<>();
        for (Like like : likes) {
            LikeResponse likeResponse = new LikeResponse();
            likeResponse.setId(like.getId());
            likeResponse.setUserId(like.getUser().getId());
            likeResponse.setPostId(like.getPost().getId());
            likeResponses.add(likeResponse);
        }
        return likeResponses;
    }
}

