package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TimelineRepository;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
@Service("timelineService")
public class SimpleTimelineService implements TimelineService{
    private final TimelineRepository timelineRepository;

    @Autowired
    public SimpleTimelineService(TimelineRepository timelineRepository) {
        this.timelineRepository = timelineRepository;
    }

    @Override
    public Timeline find(User user) {
        return timelineRepository.find(user);
    }

    @Override
    public void save(Timeline timeline) {
        timelineRepository.save(timeline);
    }

    @Override
    public Iterable<Timeline> findAll() {
        return timelineRepository.findAll();
    }

    @Override
    public void update(Timeline timeline) {
        timelineRepository.update(timeline);
    }

    @Override
    public void updateTimelinesBatch(List<User> users, Tweet tweet) {
        users.stream()
                .filter(mentionedUser -> !tweet.getUser().equals(mentionedUser))
                .forEach(mentionedUser -> {
                    Timeline timeline = find(mentionedUser);
                    timeline.put(tweet);
                    timelineRepository.update(timeline);
                });
    }
}
