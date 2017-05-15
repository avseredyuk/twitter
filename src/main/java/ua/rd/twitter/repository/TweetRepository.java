package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.List;
import java.util.Optional;

public interface TweetRepository {
    void save(Tweet tweet);
    List<Tweet> findAll();
    List<Tweet> find(User user);
    Tweet find(Long id);
    void delete(Tweet tweet);
    void update(Tweet tweet);
    void deleteFromAllTimelines(Tweet tweet);
}