package ua.rd.twitter.repository.maria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TimelineRepository;

import java.util.List;

/**
 * Created by Anton_Serediuk on 5/11/2017.
 */
@Repository
@Primary
public class MariaDBTimelineRepository implements TimelineRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MariaDBTimelineRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Timeline timeline) {
        sessionFactory.getCurrentSession()
                .persist(timeline);
    }

    @Override
    public Timeline find(User user) {
        return (Timeline) sessionFactory.getCurrentSession()
                .createQuery("from Timeline where user_id = :user_id")
                .setLong("user_id", user.getId())
                .uniqueResult();
    }

    @Override
    public void delete(Timeline timeline) {
        sessionFactory.getCurrentSession()
                .delete(timeline);
    }

    @Override
    public void addTweetToTimeline(Timeline timeline, Tweet tweet) {
        timeline.put(tweet);
        sessionFactory.getCurrentSession()
                .update(timeline);
    }

    @Override
    public void removeTweetFromTimeline(Timeline timeline, Tweet tweet) {
        timeline.remove(tweet);
        sessionFactory.getCurrentSession()
                .update(timeline);
    }

    @Override
    public List<Timeline> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Timeline")
                .list();
    }
}
