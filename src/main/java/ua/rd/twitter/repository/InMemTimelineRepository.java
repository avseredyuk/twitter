package ua.rd.twitter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
@Repository("timelineRepository")
public class InMemTimelineRepository implements TimelineRepository {
    private final TweetRepository tweetRepository;

    @Autowired
    public InMemTimelineRepository(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Timeline compose(User user) {
        Iterable<Tweet> tweetsByUser = tweetRepository.findByUser(user);
        Timeline timeline = new Timeline(user);
        timeline.putAll(tweetsByUser);
        return timeline;
    }
}
