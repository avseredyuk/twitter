package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

public interface TweetRepository {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
    Iterable<Tweet> find(User user);
    Tweet find(int id);
    void delete(Tweet tweet);
}