package ua.rd.twitter.web.rest.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;
import ua.rd.twitter.web.app.TweetController;
import ua.rd.twitter.web.app.UserController;
import ua.rd.twitter.web.rest.RestTweetController;

import java.util.Optional;

/**
 * Created by Anton_Serediuk on 4/19/2017.
 */
@RestControllerAdvice(assignableTypes = {RestTweetController.class})
public class TweetControllerAdvice {
    private final TweetService tweetService;

    @Autowired
    public TweetControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tweet.class,
                new PropertiesEditor() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        try {
                            Long id = Long.valueOf(text);
                            Optional<Tweet> tweet = tweetService.find(id);
                            setValue(tweet.orElse(null));
                        } catch (NumberFormatException e) {
                            //todo: log
                        }
                    }
                });
    }
}
