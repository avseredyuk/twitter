package ua.rd.twitter.repository;

import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/18/2017.
 */
public interface TimelineRepository {
    Timeline compose(User user);
}
