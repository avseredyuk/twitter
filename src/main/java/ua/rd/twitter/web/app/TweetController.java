package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;

/**
 *
 * @author andrii
 */
@Controller
@RequestMapping("/tweet")
public class TweetController {
    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping("/all")
    @GetMapping
    public String allTweets(Model model) {
        Iterable<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweets", tweets);
        return "tweet/all";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String tweet() {
        return "tweet/create";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String createTweet(User user, String text) {
        Tweet tweet = tweetService.createTweet(text, user);
        tweetService.save(tweet);
        return "tweet/all";
    }

    @RequestMapping("/delete/{id}")
    @PostMapping
    public String deleteTweet(@PathVariable("id") Integer id) {
        Tweet tweet = new Tweet(id);
        tweetService.delete(tweet);
        System.out.println(tweet);
        return "tweet/all";
    }


    
}
