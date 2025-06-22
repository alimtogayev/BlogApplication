package alim.project.blogapp.dto;
import java.util.List;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private List<Long> likesList;
    private List<Long> commentsList;

    public PostResponse() {
    }

    public PostResponse(Long id, String title, String content, Long userId, List<Long> likesList, List<Long> commentsList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.likesList = likesList;
        this.commentsList = commentsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Long> likesList) {
        this.likesList = likesList;
    }

    public List<Long> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Long> commentsList) {
        this.commentsList = commentsList;
    }
}
