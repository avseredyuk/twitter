package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TweetRepository;

@Service("tweetService")
public class SimpleTweetService implements TweetService {
    private final TweetRepository tweetRepository;

    @Autowired
    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet createTweet(String text, User user) throws Exception {
        Tweet newTweet = createEmptyTweet();
        newTweet.setText(text);
        return newTweet;
    }

    @Override
    public void save(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public Iterable<Tweet> findAll() {
        System.out.println("Called");
        return tweetRepository.findAll();
    }

    @Lookup()
    Tweet createEmptyTweet() throws Exception {
        return null;
    }

}
