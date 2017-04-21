package ua.rd.twitter.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tweet {
    private User user;
    private String text;
    private List<User> likes = new ArrayList<>();
    private List<User> mentionedUsers = new ArrayList<>();
    private Long id;
    private Tweet replyTo;
    private Tweet retweetOf;

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
        List<User> filteredUsers = users.stream().filter(u -> !u.equals(this.user))
                .collect(Collectors.toList());
        mentionedUsers.addAll(filteredUsers);
    }

    public List<User> getLikeUsers() {
        return new ArrayList<>(likes);
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

    public Tweet getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Tweet replyTo) {
        this.replyTo = replyTo;
    }

    public Tweet getRetweetOf() {
        return retweetOf;
    }

    public void setRetweetOf(Tweet retweetOf) {
        this.retweetOf = retweetOf;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", mentionedUsers=" + mentionedUsers +
                ", id=" + id +
                '}';
    }
}
