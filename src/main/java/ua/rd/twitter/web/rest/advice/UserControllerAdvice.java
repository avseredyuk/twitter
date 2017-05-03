package ua.rd.twitter.web.rest.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.UserService;
import ua.rd.twitter.web.rest.RestTweetController;
import ua.rd.twitter.web.rest.RestUserController;

/**
 * Created by Anton_Serediuk on 5/4/2017.
 */
@RestControllerAdvice(assignableTypes = {RestUserController.class})
public class UserControllerAdvice {
    private final UserService userService;

    @Autowired
    public UserControllerAdvice(UserService userService) {
        this.userService = userService;
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
