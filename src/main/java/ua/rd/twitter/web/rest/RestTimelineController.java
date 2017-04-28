package ua.rd.twitter.web.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.rd.twitter.domain.Timeline;

/**
 * Created by Anton_Serediuk on 4/28/2017.
 */
@RestController
public class RestTimelineController {

    @RequestMapping(value = "/timeline/{username}",
            method = RequestMethod.GET)
    public Timeline userTimeline(@PathVariable("username") Timeline timeline) {
        return timeline;
    }
}
