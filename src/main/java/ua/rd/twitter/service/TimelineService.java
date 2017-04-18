package ua.rd.twitter.service;

import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
public interface TimelineService {
    Timeline compose(User user);
}
