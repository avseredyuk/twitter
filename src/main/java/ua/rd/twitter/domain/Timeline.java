package ua.rd.twitter.domain;

import java.util.ArrayList;
import java.util.List;


public class Timeline {
    private final List<Tweet> tweets = new ArrayList<>();
    private final User user;

    public Timeline(User user) {
        this.user = user;
    }

    public void put(Tweet tweet) {
        tweets.add(tweet);
    }

    public void putAll(Iterable<Tweet> tweetsSource) {
        tweetsSource.forEach(tweets::add);
    }

    public Iterable<Tweet> getTweets() {
        return new ArrayList<>(tweets);
    }

    public User getUser() {
        return user;
    }
    
}
