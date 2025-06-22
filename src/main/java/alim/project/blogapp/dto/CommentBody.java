package alim.project.blogapp.dto;

public class CommentBody {
    private String content;

    public CommentBody() {
        // Default constructor
    }

    public CommentBody(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
