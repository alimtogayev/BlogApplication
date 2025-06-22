package alim.project.blogapp.dto;

import alim.project.blogapp.entities.Post;

import java.util.List;

public class UserResponse {
    private Long id;
    private String name;
    private List<Long> likeList;
    private List<Long> postList;
    private List<Long> commentList;

    public UserResponse(Long id, String name, List<Long> likeList, List<Long> postList, List<Long> commentList) {
        this.id = id;
        this.name = name;
        this.likeList = likeList;
        this.postList = postList;
        this.commentList = commentList;
    }

    public UserResponse() {
        // Default constructor
    }

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

    public List<Long> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Long> likeList) {
        this.likeList = likeList;
    }

    public List<Long> getPostList() {
        return postList;
    }

    public void setPostList(List<Long> postList) {
        this.postList = postList;
    }

    public List<Long> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Long> commentList) {
        this.commentList = commentList;
    }
}
