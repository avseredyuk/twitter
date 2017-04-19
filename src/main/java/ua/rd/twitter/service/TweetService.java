package ua.rd.twitter.service;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.Iterator;

public interface TweetService {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
    Iterable<Tweet> find(User user);
    Tweet find(int id);
    Tweet createTweet(String text, User user);
    void delete(Tweet tweet);
}