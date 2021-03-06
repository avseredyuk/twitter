package ua.rd.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/user",
                    method = RequestMethod.GET)
    public List<User> allUsers() {
        return userService.findAll();
    }

    @RequestMapping(
            value = "/user/{username}",
            method = RequestMethod.GET)
    public User getUserByName(@PathVariable("username") User user) {
        return user;
    }

    @RequestMapping(
            value = "/user/{username}",
            method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("username") User user) {
        userService.delete(user);
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.PUT)
    public void updateTweet(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            consumes = "application/json")
    public void newUser(@RequestBody User user) {
        userService.save(user);
    }
}
