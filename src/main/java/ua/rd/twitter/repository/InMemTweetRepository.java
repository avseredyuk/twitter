package ua.rd.twitter.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;

@Repository("tweetRepository")
public class InMemTweetRepository implements TweetRepository  {
    private List<Tweet> tweets = new ArrayList<>();

    @Autowired
    private ArrayList<Tweet> testTweets;
    
    @PostConstruct
    public void init(){
        tweets.addAll(testTweets);
    }
    
    @Override
    public void save(Tweet tweet) {
        tweets.add(tweet);
    }
    
    @Override
    public Iterable<Tweet> findAll() {
        return new ArrayList(tweets);
    }

    @Override
    public Iterable<Tweet> find(User user) {
        return tweets.stream()
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Tweet tweet) {
        tweets = tweets.stream()
                .filter(t -> !t.getId().equals(tweet.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Tweet find(int id) {
        return tweets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .get();
    }
}
