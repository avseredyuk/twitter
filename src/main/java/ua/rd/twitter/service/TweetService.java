package ua.rd.twitter.service;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

public interface TweetService {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
    Tweet createTweet(String text, User user) throws Exception;
}