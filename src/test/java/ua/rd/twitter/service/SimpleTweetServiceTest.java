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
    private SimpleTweetService tweetService = null;

    @Test
    public void findFound() throws Exception {
        System.out.println("find by id");
        Long id = 0L;
        Optional<Tweet> result = tweetService.find(id);
        assertNotNull(result.orElse(null));
    }

    @Test
    public void findNotFound() throws Exception {
        System.out.println("find by id");
        Long id = 100L;
        Optional<Tweet> result = tweetService.find(id);
        assertNull(result.orElse(null));
    }

}