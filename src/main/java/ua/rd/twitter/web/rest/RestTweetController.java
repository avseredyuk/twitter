package ua.rd.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/24/2017.
 */
@RestController
public class RestTweetController {
    private final TweetService tweetService;

    @Autowired
    public RestTweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping(
            value = "/tweet/all",
            method = RequestMethod.GET)
    public List<Tweet> allTweets() {
        return tweetService.findAll();
    }

    @RequestMapping(
            value = "/tweet/{tweetId}",
            method = RequestMethod.GET)
    public Tweet getTweetById(@PathVariable("tweetId") Tweet tweet) {
        return tweet;
    }

    @RequestMapping(
            value = "/tweet",
            method = RequestMethod.POST,
            consumes = "application/json")
    public Tweet newTweet(@RequestBody Tweet tweet) {
        tweetService.save(tweet);
        return tweet;
    }
}

