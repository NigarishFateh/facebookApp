package model;

public class Comment {

    private int id;
    private String text;
    private User commentedBy;

    public Comment(int id, String text, User commentedBy) {
        this.id = id;
        this.text = text;
        this.commentedBy = commentedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(User commentedBy) {
        this.commentedBy = commentedBy;
    }

    @Override
    public String toString() {
        return commentedBy.getName() + "\n" + text;
    }
}
