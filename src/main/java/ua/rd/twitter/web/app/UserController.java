package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/all";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String showCreateUser() {
        return "user/create";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createUser(User user) {
        userService.save(user);
        return new ModelAndView("redirect:/web/user/all");
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String showEditUser(@PathVariable("username") User user, Model model) {
        model.addAttribute("user", user);
        return "user/edit";
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.POST)
    public ModelAndView editUser(User user) {
        userService.update(user);
        return new ModelAndView("redirect:/web/user/all");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UnsupportedOperationException.class)
    public String onException(Exception e, Model model) {
        model.addAttribute("error", e);
        return "error";
    }

}
