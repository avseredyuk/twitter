package ua.rd.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TimelineService;
import ua.rd.twitter.service.UserService;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
@Controller
@RequestMapping("timeline")
public class TimelineController {

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String userTimeline(@PathVariable("username") Timeline timeline,
                               @PathVariable("username") User user,
                               Model model) {
        model.addAttribute("timeline", timeline);
        model.addAttribute("user", user);
        return "timeline";
    }


}
