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
    void save(Timeline timeline);
    Iterable<Timeline> findAll();
    void update(Timeline timeline);
    void updateTimelinesBatch(List<User> users, Tweet tweet);
}
