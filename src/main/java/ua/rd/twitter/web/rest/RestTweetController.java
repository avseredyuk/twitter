package ua.rd.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
            value = "/tweet",
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
            value = "/tweet/{tweetId}",
            method = RequestMethod.DELETE)
    public void deleteTweet(@PathVariable("tweetId") Tweet tweet) {
        tweetService.delete(tweet);
    }

    @RequestMapping(
            value = "/tweet",
            method = RequestMethod.PUT)
    public void updateTweet(@RequestBody Tweet tweet) {
        tweetService.update(tweet);
    }

    @RequestMapping(
            value = "/tweet",
            method = RequestMethod.POST,
            consumes = "application/json")
    public void newTweet(@RequestBody Tweet tweet) {
        tweetService.save(tweet);
    }


}

