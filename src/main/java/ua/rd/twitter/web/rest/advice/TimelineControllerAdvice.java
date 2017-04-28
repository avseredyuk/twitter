package ua.rd.twitter.web.rest.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.service.TimelineService;
import ua.rd.twitter.service.UserService;
import ua.rd.twitter.web.rest.RestTimelineController;

/**
 * Created by Anton_Serediuk on 4/28/2017.
 */
@RestControllerAdvice(assignableTypes = {RestTimelineController.class})
public class TimelineControllerAdvice {
    private final TimelineService timelineService;
    private final UserService userService;

    @Autowired
    public TimelineControllerAdvice(TimelineService timelineService, UserService userService) {
        this.timelineService = timelineService;
        this.userService = userService;
    }

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timeline.class,
                new PropertiesEditor() {
                    @Override
                    public void setAsText(String username) throws IllegalArgumentException {
                        User user = userService.find(username);
                        Timeline timeline = timelineService.find(user);
                        setValue(timeline);
                    }
                });
    }
}
