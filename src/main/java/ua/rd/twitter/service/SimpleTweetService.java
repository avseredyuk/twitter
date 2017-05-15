package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TweetRepository;

import java.util.ArrayList;
import java.util.List;
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
    public Tweet createTweet(String text, User user, Tweet replyToTweet, Tweet retweetTweet) {
        Tweet newTweet = createEmptyTweet();
        newTweet.setText(text);
        newTweet.setUser(user);
        if (replyToTweet != null) {
            newTweet.setReplyTo(replyToTweet);
        }
        if (retweetTweet != null) {
            newTweet.setRetweetOf(retweetTweet);
            retweetTweet.setRetweetsCount(retweetTweet.getRetweetsCount() + 1);
        }
        return newTweet;
    }

    @Override
    @Transactional
    public void save(Tweet tweet) {
        User user = userService.find(tweet.getUser().getName());
        tweet.setUser(user);
        tweetRepository.save(tweet);
        timelineService.addTweetToTimeline(user, tweet);
        addToMentionedTimelines(tweet);
        addToRepliedTweetTimeline(tweet);
    }

    @Override
    @Transactional
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Lookup("tweet")
    public Tweet createEmptyTweet() {
        return null;
    }

    @Override
    @Transactional
    public List<Tweet> find(User user) {
        return tweetRepository.find(user);
    }

    @Override
    @Transactional
    public Tweet find(Long id) {
        return tweetRepository.find(id);
    }

    @Override
    @Transactional
    public void delete(Tweet tweet) {
        deleteFromAllTimelines(tweet);
        tweetRepository.delete(tweet);
    }

    @Override
    @Transactional
    public void update(Tweet tweet) {
        tweetRepository.update(tweet);
    }

    private void deleteFromAllTimelines(Tweet tweet) {
        tweetRepository.deleteFromAllTimelines(tweet);
    }

    private void addToRepliedTweetTimeline(Tweet tweet) {
        Tweet replyToTweet = tweet.getReplyTo();
        if (replyToTweet != null) {
            User repliedUser = replyToTweet.getUser();
            timelineService.addTweetToTimeline(repliedUser, tweet);
        }
    }

    private void addToMentionedTimelines(Tweet tweet) {
        List<String> mentionedUserNames = getMentionedUserNames(tweet);
        if (!mentionedUserNames.isEmpty()) {
            List<User> mentionedUsers = userService.findAllByUsernameList(mentionedUserNames);
            tweet.setMentionedUsers(mentionedUsers);
            timelineService.addTweetToTimelinesBatch(mentionedUsers, tweet);
        }
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

}
