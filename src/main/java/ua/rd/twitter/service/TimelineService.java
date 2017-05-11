package ua.rd.twitter.service;

import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
public interface TimelineService {
    Timeline find(User user);
    Timeline create(User user);
    void delete(User user);
    List<Timeline> findAll();
    void addTweetToTimeline(User user, Tweet tweet);
    void addTweetToTimelinesBatch(List<User> users, Tweet tweet);
    void removeTweetFromTimelinesBatch(List<User> users, Tweet tweet);
}
