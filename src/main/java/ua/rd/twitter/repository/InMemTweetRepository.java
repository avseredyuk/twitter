package ua.rd.twitter.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository  {
    private List<Tweet> tweets = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        tweets.add(new Tweet(new User("2del1"), "First tweeet"));
        tweets.add(new Tweet(new User("2del2"), "Second tweeet"));
    }
    
    @Override
    public void save(Tweet tweet) {
        tweets.add(tweet);
    }
    
    @Override
    public Iterable<Tweet> findAll() {
        return new ArrayList(tweets);
    }
}
