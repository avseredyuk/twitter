package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TweetService;
import ua.rd.twitter.service.UserService;

import java.util.Optional;

/**
 * Created by Anton_Serediuk on 4/19/2017.
 */
@ControllerAdvice(assignableTypes = {TweetController.class})
public class TweetControllerAdvice {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

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
