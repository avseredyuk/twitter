package ua.rd.twitter.repository.maria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.Tweet;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.TweetRepository;

import java.util.List;

/**
 * Created by Anton_Serediuk on 5/10/2017.
 */
@Repository
@Primary
public class MariaDBTweetRepository implements TweetRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MariaDBTweetRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Tweet tweet) {
        sessionFactory.getCurrentSession()
                .persist(tweet);
    }

    @Override
    public List<Tweet> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Tweet")
                .list();
    }

    @Override
    public List<Tweet> find(User user) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Tweet where user = :user")
                .setString("user", user.getName())
                .list();
    }

    @Override
    public Tweet find(Long id) {
        return (Tweet) sessionFactory.getCurrentSession()
                .createQuery("from Tweet where id = :id")
                .setLong("id", id)
                .uniqueResult();
    }

    @Override
    public void delete(Tweet tweet) {
        sessionFactory.getCurrentSession()
                .delete(tweet);
    }

    @Override
    public void update(Tweet tweet) {
        sessionFactory.getCurrentSession()
                .update(tweet);
    }
}
