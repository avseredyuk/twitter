package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Timeline create(User user) {
        Timeline newTimeline = createEmptyTimeline();
        newTimeline.setUser(user);
        timelineRepository.save(newTimeline);
        return newTimeline;
    }

    @Lookup("timeline")
    public Timeline createEmptyTimeline() {
        return null;
    }

    @Override
    @Transactional
    public Timeline find(User user) {
        return timelineRepository.find(user);
    }

    @Override
    @Transactional
    public List<Timeline> findAll() {
        return timelineRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        Timeline timeline = timelineRepository.find(user);
        timelineRepository.delete(timeline);
    }

    @Override
    @Transactional
    public void addTweetToTimeline(User user, Tweet tweet) {
        Timeline timeline = timelineRepository.find(user);
        timelineRepository.addTweetToTimeline(timeline, tweet);
    }

    @Override
    @Transactional
    public void addTweetToTimelinesBatch(List<User> users, Tweet tweet) {
        users.stream()
                .filter(mentionedUser -> !tweet.getUser().equals(mentionedUser))
                .forEach(mentionedUser ->
                        addTweetToTimeline(mentionedUser, tweet)
                );
    }

    @Override
    @Transactional
    public void removeTweetFromTimelinesBatch(List<User> users, Tweet tweet) {
        System.out.println("**********************************************************************");
        users.stream()
                .forEach(mentionedUser -> {
                    Timeline timeline = timelineRepository.find(mentionedUser);
                    System.out.println(timeline);
                    timelineRepository.removeTweetFromTimeline(timeline, tweet);
                });
        System.out.println("**********************************************************************");
    }
}
