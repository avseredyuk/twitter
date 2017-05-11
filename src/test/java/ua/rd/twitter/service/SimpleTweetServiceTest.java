package ua.rd.twitter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rd.twitter.domain.Tweet;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Anton_Serediuk on 4/19/2017.
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(locations = {"classpath:/repoContext.xml"}),
        @ContextConfiguration(locations = {"classpath:/serviceContext.xml"})
})

public class SimpleTweetServiceTest {
    @Autowired
    private TweetService tweetService;

    @Test
    public void findFound() throws Exception {
        Long id = 1L;
        Tweet result = tweetService.find(id);
        assertNotNull(result);
    }

    @Test
    public void findNotFound() throws Exception {
        Long id = 100L;
        Tweet result = tweetService.find(id);
        assertNull(result);
    }

}