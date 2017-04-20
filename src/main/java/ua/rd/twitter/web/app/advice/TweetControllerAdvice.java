package ua.rd.twitter.web.app.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;
import ua.rd.twitter.web.app.TimelineController;
import ua.rd.twitter.web.app.TweetController;
import ua.rd.twitter.web.app.UserController;

import java.util.Optional;

/**
 * Created by Anton_Serediuk on 4/19/2017.
 */
@ControllerAdvice(assignableTypes = {TweetController.class})
public class TweetControllerAdvice {
    private final TweetService tweetService;
    private final UserService userService;

    @Autowired
    public TweetControllerAdvice(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tweet.class,
                new PropertiesEditor() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        Long id = Long.valueOf(text);
                        Optional<Tweet> tweet = tweetService.find(id);
                        setValue(tweet.orElse(null));
                    }
                });
    }

    @InitBinder
    public void userBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class,
                new PropertiesEditor() {
                    @Override
                    public void setAsText(String userName) throws IllegalArgumentException {
                        User user = userService.find(userName);
                        setValue(user);
                    }
                });
    }
}
