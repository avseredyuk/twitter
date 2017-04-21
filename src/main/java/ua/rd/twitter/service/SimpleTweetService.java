package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("tweetService")
public class SimpleTweetService implements TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final TimelineService timelineService;

    @Autowired
    public SimpleTweetService(TweetRepository tweetRepository, UserService userService, TimelineService timelineService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
        this.timelineService = timelineService;
    }

    @Override
    public Tweet createTweet(String text, User user, Tweet replyToTweet) {
        Tweet newTweet = createEmptyTweet();
        newTweet.setText(text);
        newTweet.setUser(user);
        if (replyToTweet != null) {
            newTweet.setReplyTo(replyToTweet);
        }
        return newTweet;
    }

    @Override
    public void save(Tweet tweet) {
        tweetRepository.save(tweet);
        timelineService.find(tweet.getUser()).put(tweet);
    }

    @Override
    public void saveAndAddToMentionedTimelines(Tweet tweet) {
        save(tweet);
        addToMentionedTimelines(tweet);
    }

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Lookup("tweet")
    public Tweet createEmptyTweet() {
        return null;
    }

    @Override
    public List<Tweet> find(User user) {
        return tweetRepository.find(user);
    }

    @Override
    public Optional<Tweet> find(Long id) {
        return tweetRepository.find(id);
    }

    @Override
    public void delete(Tweet tweet) {
        tweetRepository.delete(tweet);
        deleteFromMentionedTimelines(tweet);
    }

    private void addToMentionedTimelines(Tweet tweet) {
        List<String> mentionedUserNames = getMentionedUserNames(tweet);
        List<User> mentionedUsers = userService.findAllByUsernameList(mentionedUserNames);
        tweet.setMentionedUsers(mentionedUsers);
        timelineService.addTweetToTimelinesBatch(mentionedUsers, tweet);
    }

    private List<String> getMentionedUserNames(Tweet tweet) {
        String tweetText = tweet.getText();
        List<String> mentionedUserNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("@([\\w\\d]+)");
        Matcher matcher = pattern.matcher(tweetText);
        while (matcher.find()) {
            String userName = matcher.group(1);
            mentionedUserNames.add(userName);
        }
        return mentionedUserNames;
    }

    private void deleteFromMentionedTimelines(Tweet tweet) {
        List<String> mentionedUserNames = getMentionedUserNames(tweet);
        List<User> mentionedUsers = userService.findAllByUsernameList(mentionedUserNames);
        timelineService.removeTweetFromTimelinesBatch(mentionedUsers, tweet);
    }
}
