package dev.richst.commentapi.restmodel;

public class Comment {
    public Comment(String comment) {
        this.comment = comment;
    }

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
