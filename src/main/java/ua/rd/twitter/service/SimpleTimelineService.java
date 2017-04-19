package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TimelineRepository;
import ua.rd.twitter.repository.TweetRepository;
import ua.rd.twitter.repository.UserRepository;

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
}
