package alim.project.blogapp.dto;

public class PostInfo {
    private String contentInfo;
    private String titleInfo;

    public PostInfo() {
        // Default constructor
    }

    public PostInfo(String contentInfo, String titleInfo) {
        this.contentInfo = contentInfo;
        this.titleInfo = titleInfo;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo= contentInfo;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }
}
