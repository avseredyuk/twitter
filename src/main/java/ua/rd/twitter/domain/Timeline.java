package ua.rd.twitter.domain;

import java.util.ArrayList;
import java.util.List;


public class Timeline {
    private List<Tweet> tweets = new ArrayList<>();
    private User user;
    private Long id;

    public Timeline() {
    }

    public Timeline(List<Tweet> tweets, User user, Long id) {
        this.tweets.addAll(tweets);
        this.user = user;
        this.id = id;
    }

    public void put(Tweet tweet) {
        tweets.add(tweet);
    }

    public void putAll(Iterable<Tweet> tweetsSource) {
        tweetsSource.forEach(this::put);
    }

    public Iterable<Tweet> getTweets() {
        return new ArrayList<>(tweets);
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void remove(Tweet tweet) {
        tweets.remove(tweet);
    }
}
