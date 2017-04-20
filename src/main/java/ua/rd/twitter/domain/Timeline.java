package ua.rd.twitter.domain;

import java.util.ArrayList;
import java.util.List;


public class Timeline {
    private final List<Tweet> tweets;
    private final User user;
    private final Long id;

    public Timeline(List<Tweet> tweets, User user, Long id) {
        this.tweets = tweets;
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
}
