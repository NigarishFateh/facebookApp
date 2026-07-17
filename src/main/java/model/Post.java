package model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private int id;
    private String caption;
    private User author;
    private List<Like> likes;
    private List<Comment> comments;

    public Post(int id, String caption, User author) {
        this.id = id;
        this.caption = caption;
        this.author = author;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addLike(Like like) {
        this.likes.add(like);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", caption='" + caption + "', author=" + author.getName() + "}";
    }
}
