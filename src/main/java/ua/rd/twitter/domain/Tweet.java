package ua.rd.twitter.domain;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    private User user;
    private String text;
    private List<User> likes = new ArrayList<>();
    private List<User> mentionedUsers = new ArrayList<>();
    private Long id;

    public Tweet() {
    }

    public Tweet(Long id) {
        this.id = id;
    }

    public Tweet(User user, String text, Long id) {
        this.user = user;
        this.text = text;
        this.id = id;
    }

    public void like(User user) {
        if (!likes.contains(user)) {
            likes.add(user);
        }
    }

    public void setMentionedUsers(List<User> users) {
        mentionedUsers.addAll(users);
    }

    public int getLikesCount() {
        return likes.size();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getMentionedUsers() {
        return new ArrayList<>(mentionedUsers);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", id=" + id +
                '}';
    }
}
