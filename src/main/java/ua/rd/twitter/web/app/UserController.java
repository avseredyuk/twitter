package ua.rd.twitter.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String user() {
        return "/createUser";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public @ResponseBody String createUser(User user) {
//        User user = new User(name);
        return user.toString();
    }

    @ModelAttribute("user")
    public User unnamedUser() {
        System.out.println("unn");
        return new User("noname");
    }
}
