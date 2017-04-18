package ua.rd.twitter.service;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.Iterator;

public interface TweetService {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
    Iterable<Tweet> findByUser(User user);
    Tweet createTweet(String text, User user) throws Exception;
}