package model;

public class Like {

    private int id;
    private User likedBy;

    public Like(int id, User likedBy) {
        this.id = id;
        this.likedBy = likedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(User likedBy) {
        this.likedBy = likedBy;
    }

    @Override
    public String toString() {
        return likedBy.getName();
    }
}
