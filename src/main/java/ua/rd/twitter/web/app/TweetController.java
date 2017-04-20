package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;

/**
 *
 * @author andrii
 */
@Controller
@RequestMapping("/tweet")
public class TweetController {
    private final TweetService tweetService;
    private final UserService userService;

    @Autowired
    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @RequestMapping("/all")
    @GetMapping
    public String allTweets(Model model) {
        Iterable<Tweet> tweets = tweetService.findAll();
        model.addAttribute("tweets", tweets);
        return "tweet/all";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String tweet() {
        return "tweet/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createTweet(@RequestParam("username") User user, String text) {
//        User user = userService.find(userName);
        Tweet tweet = tweetService.createTweet(text, user);
        tweetService.saveAndProcessMentions(tweet);
        return new ModelAndView("redirect:/web/tweet/all");
    }

    @RequestMapping(value = "/tweet", method = RequestMethod.GET)
    public @ResponseBody String tweetById(@RequestParam("id") Long id) {
        return tweetService.find(id)
                .map(Tweet::toString).orElse("Eggog");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody String update(@RequestParam("id") Tweet tweet) {
        return tweet.toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteTweet(@RequestParam("id") Tweet tweet) {
        tweetService.delete(tweet);
        return new ModelAndView("redirect:/web/tweet/all");
    }

}
