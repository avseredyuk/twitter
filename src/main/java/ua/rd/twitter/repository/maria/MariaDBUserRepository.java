package ua.rd.twitter.repository.maria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.UserRepository;

import java.util.List;

/**
 * Created by Anton_Serediuk on 5/10/2017.
 */
@Repository
@Primary
public class MariaDBUserRepository implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MariaDBUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession()
                .save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession()
                .update(user);
    }

    @Override
    public User find(String name) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where name = :name")
                .setString("name", name)
                .uniqueResult();
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .list();
    }

    @Override
    public List<User> findAllByUsernameList(List<String> userNames) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where name in (:names)")
                .setParameterList("names", userNames)
                .list();
    }
}
