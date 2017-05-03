package ua.rd.twitter.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        int index = tweets.size();
        tweets.add(tweet);
        tweet.setIdd((long) index);
    }
    
    @Override
    public List<Tweet> findAll() {
        return new ArrayList<>(tweets);
    }

    @Override
    public List<Tweet> find(User user) {
        return tweets.stream()
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Tweet tweet) {
        tweets = tweets.stream()
                .filter(t -> t.getIdd() != null)
                .filter(t -> t.getIdd().longValue() != tweet.getIdd().longValue())
                .collect(Collectors.toList());
    }

    @Override
    public void update(Tweet tweet) {
        for (int i = 0; i < tweets.size(); i++ ) {
            Tweet t = tweets.get(i);
            if ((t.getIdd() != null) && (t.getIdd().longValue() == tweet.getIdd().longValue())) {
                tweets.set(i, tweet);
                break;
            }
        }
    }

    @Override
    public Optional<Tweet> find(Long id) {
        Optional<Tweet> tweet;
        try {
            tweet = tweets.stream()
                    .filter(t -> t.getIdd().equals(id))
                    .findFirst();
        } catch (IndexOutOfBoundsException e) {
            tweet = Optional.empty();
        }
        return tweet;
    }
}
