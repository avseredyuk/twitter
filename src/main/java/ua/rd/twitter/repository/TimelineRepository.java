package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
public interface TimelineRepository {
    Timeline find(User user);
    void save(Timeline timeline);
    void delete(Timeline timeline);
    void addTweetToTimeline(Timeline timeline, Tweet tweet);
    void removeTweetFromTimeline(Timeline timeline, Tweet tweet);
    List<Timeline> findAll();
}
