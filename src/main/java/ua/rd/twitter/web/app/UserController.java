package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.UserService;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String user() {
        return "user/create";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String createUser(User user) {
        userService.save(user);
        //TODO fix redirect here
        return "user/all";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping
    public String allUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/all";
    }

}
