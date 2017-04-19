package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.Optional;

public interface TweetRepository {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
    Iterable<Tweet> find(User user);
    Optional<Tweet> find(Long id);
    void delete(Tweet tweet);
}