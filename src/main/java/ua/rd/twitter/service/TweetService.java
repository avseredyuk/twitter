package ua.rd.twitter.service;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.List;
import java.util.Optional;

public interface TweetService {
    void save(Tweet tweet);
    List<Tweet> findAll();
    List<Tweet> find(User user);
    Tweet find(Long id);
    Tweet createTweet(String text, User user, Tweet replyToTweet, Tweet retweetTweet);
    void delete(Tweet tweet);
    void update(Tweet tweet);
}