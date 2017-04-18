package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Tweet;

public interface TweetRepository {
    void save(Tweet tweet);
    Iterable<Tweet> findAll();
}