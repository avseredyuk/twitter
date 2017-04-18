package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.service.TweetService;

/**
 *
 * @author andrii
 */
@Controller
public class TweetsController {
    private final TweetService tweetService;

    @Autowired
    public TweetsController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @RequestMapping(value = "/tweets")
    @GetMapping
    public String allTweets(Model model) {
        Iterable<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweets", tweets);
        return "tweets";
    }
    
}
