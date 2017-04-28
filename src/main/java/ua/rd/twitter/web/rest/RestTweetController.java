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
            value = "/tweet/all",
            method = RequestMethod.GET)
    public List<Tweet> allTweets() {
        return tweetService.findAll();
    }

    @RequestMapping(
            value = "/tweet/{tweetId}",
            method = RequestMethod.GET)
    public Tweet getTweetById(@PathVariable("tweetId") Long id) {
        return tweetService.find(id).get();
    }

    @RequestMapping(
            value = "/tweet",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Tweet> newTweet(@RequestBody Tweet tweet) {
        tweetService.save(tweet);
        Link link = linkTo(methodOn(RestTweetController.class).getTweetById(tweet.getIdd())).withSelfRel();
        tweet.add(link);

        return new ResponseEntity<>(tweet, HttpStatus.CREATED);
    }

//    @RequestMapping(
//            value = "/tweet",
//            method = RequestMethod.POST,
//            consumes = "application/json")
//    public ResponseEntity<Void> newTweet(@RequestBody Tweet tweet,
//                                         UriComponentsBuilder builder) {
//        tweetService.save(tweet);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(
//                builder.path("/tweet/{tweetID}")
//                        .buildAndExpand(tweet.getId()).toUri());
//
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
}

