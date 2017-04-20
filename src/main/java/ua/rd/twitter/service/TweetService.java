package ua.rd.twitter.service;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.List;
import java.util.Optional;

public interface TweetService {
    void save(Tweet tweet);
    void saveAndProcessMentions(Tweet tweet);
    List<Tweet> findAll();
    List<Tweet> find(User user);
    Optional<Tweet> find(Long id);
    Tweet createTweet(String text, User user);
    void delete(Tweet tweet);
}