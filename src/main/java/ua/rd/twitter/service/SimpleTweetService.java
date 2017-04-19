package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TimelineRepository;
import ua.rd.twitter.repository.TweetRepository;
import ua.rd.twitter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("tweetService")
public class SimpleTweetService implements TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;
    private final TimelineRepository timelineRepository;

    @Autowired
    public SimpleTweetService(TweetRepository tweetRepository, UserRepository userRepository, TimelineRepository timelineRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.timelineRepository = timelineRepository;
    }

    @Override
    public Tweet createTweet(String text, User user) {
        Tweet newTweet = createEmptyTweet();
        newTweet.setText(text);
        newTweet.setUser(user);
        return newTweet;
    }

    @Override
    public void save(Tweet tweet) {
        tweetRepository.save(tweet);
        checkMentions(tweet);
    }

    @Override
    public Iterable<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Lookup("tweet")
    public Tweet createEmptyTweet() {
        return null;
    }

    @Override
    public Iterable<Tweet> find(User user) {
        return tweetRepository.find(user);
    }

    @Override
    public Optional<Tweet> find(Long id) {
        return tweetRepository.find(id);
    }

    @Override
    public void delete(Tweet tweet) {
        tweetRepository.delete(tweet);
    }

    //todo: refactor this trash
    private void checkMentions(Tweet tweet) {
        List<User> mentionedUsers = new ArrayList<>();
        String tweetText = tweet.getText();
        Pattern pattern = Pattern.compile("@([\\w\\d]+)");
        Matcher matcher = pattern.matcher(tweetText);
        while (matcher.find()) {
            String userName = matcher.group(1);
            User user = userRepository.find(userName);
            mentionedUsers.add(user);
        }
        tweet.setMentionedUsers(mentionedUsers);
        timelineRepository.find(tweet.getUser()).put(tweet);
        for(User mentionedUser : mentionedUsers) {
            Timeline timeline = timelineRepository.find(mentionedUser);
            timeline.put(tweet);
        }
    }
}
