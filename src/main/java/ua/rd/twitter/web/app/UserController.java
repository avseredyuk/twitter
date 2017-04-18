package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TimelineService;
import ua.rd.twitter.service.UserService;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

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

    @RequestMapping("/all")
    @GetMapping
    public String allUsers(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/all";
    }

//    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
//    public @ResponseBody String qwerty(User user) {
//        return "FUCK " + user.getName();
//    }


}
