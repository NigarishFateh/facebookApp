package service;

import model.Comment;
import model.Like;
import model.Post;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class FacebookService {

    private List<User> users;
    private List<Post> posts;
    private int userIdCounter;
    private int postIdCounter;
    private int likeIdCounter;
    private int commentIdCounter;

    public FacebookService() {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.userIdCounter = 1;
        this.postIdCounter = 1;
        this.likeIdCounter = 1;
        this.commentIdCounter = 1;
    }

    public User createUser(String name, String email) {
        User user = new User(userIdCounter++, name, email);
        users.add(user);
        System.out.println("User created: " + user.getName() + " (ID: " + user.getId() + ")");
        return user;
    }

    public Post createPost(int userId, String caption) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return null;
        }
        Post post = new Post(postIdCounter++, caption, user);
        user.addPost(post);
        posts.add(post);
        System.out.println("Post created by " + user.getName() + ": \"" + caption + "\" (Post ID: " + post.getId() + ")");
        return post;
    }

    public void likePost(int postId, int userId) {
        Post post = findPostById(postId);
        User user = findUserById(userId);

        if (post == null) {
            System.out.println("Post not found with ID: " + postId);
            return;
        }
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        for (Like like : post.getLikes()) {
            if (like.getLikedBy().getId() == userId) {
                System.out.println(user.getName() + " has already liked this post.");
                return;
            }
        }

        Like like = new Like(likeIdCounter++, user);
        post.addLike(like);
        System.out.println(user.getName() + " liked the post: \"" + post.getCaption() + "\"");
    }

    public void commentOnPost(int postId, int userId, String text) {
        Post post = findPostById(postId);
        User user = findUserById(userId);

        if (post == null) {
            System.out.println("Post not found with ID: " + postId);
            return;
        }
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        Comment comment = new Comment(commentIdCounter++, text, user);
        post.addComment(comment);
        System.out.println(user.getName() + " commented on \"" + post.getCaption() + "\": " + text);
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("\n========= ALL USERS =========");
        for (User user : users) {
            System.out.println("ID: " + user.getId() + " | Name: " + user.getName() + " | Email: " + user.getEmail()
                    + " | Posts: " + user.getPosts().size());
        }
        System.out.println("=============================\n");
    }

    public void displayPosts() {
        if (posts.isEmpty()) {
            System.out.println("No posts found.");
            return;
        }

        System.out.println("\n========= ALL POSTS =========");
        for (Post post : posts) {
            System.out.println("\nUser: " + post.getAuthor().getName());
            System.out.println("Post ID: " + post.getId());
            System.out.println("Posts");
            System.out.println("--------------------------------");
            System.out.println(post.getCaption());
            System.out.println();
            System.out.println("Likes:");
            if (post.getLikes().isEmpty()) {
                System.out.println("(none)");
            } else {
                for (Like like : post.getLikes()) {
                    System.out.println(like.getLikedBy().getName());
                }
            }
            System.out.println();
            System.out.println("Comments:");
            if (post.getComments().isEmpty()) {
                System.out.println("(none)");
            } else {
                for (Comment comment : post.getComments()) {
                    System.out.println();
                    System.out.println(comment.getCommentedBy().getName());
                    System.out.println(comment.getText());
                }
            }
            System.out.println("--------------------------------");
        }
        System.out.println("=============================\n");
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Post findPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
