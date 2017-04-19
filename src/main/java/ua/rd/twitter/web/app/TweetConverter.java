package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.service.TweetService;

import java.util.Optional;

/**
 * Created by Anton_Serediuk on 4/19/2017.
 */
public class TweetConverter implements Converter<String, Tweet> {
    @Autowired
    private TweetService tweetService;

    @Override
    public Tweet convert(String s) {
        Long id = Long.valueOf(s);
        Optional<Tweet> tweet = tweetService.find(id);
        return tweet.orElse(null);
    }
}
