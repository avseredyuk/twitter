package ua.rd.twitter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Anton_Serediuk on 5/11/2017.
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:/repoContext.xml"}),
        @ContextConfiguration(locations = {"classpath:/serviceContext.xml"})
})

public class SimpleTimelineServiceTest {
    @Autowired
    private TimelineService timelineService;
    @Autowired
    private UserService userService;
    @Autowired
    private TweetService tweetService;

    @Test
    public void removeTweetFromTimelinesBatch() throws Exception {
//        User user = userService.find("dron");
//        Tweet tweet = tweetService.find(34L);
//        Timeline timeline = timelineService.find(user);
//
//        timeline.remove(tweet);
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//
//        timelineService.removeTweetFromTimelinesBatch(users, tweet);
//
//        assertNotNull(timeline);
    }

}