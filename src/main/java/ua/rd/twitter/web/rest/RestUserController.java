package ua.rd.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.UserService;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/28/2017.
 */
@RestController
public class RestUserController {
    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/all",
                    method = RequestMethod.GET)
    public List<User> allUsers() {
        return userService.findAll();
    }
}
