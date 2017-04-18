package ua.rd.twitter.repository;

import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
public interface UserRepository {
    void save(User user);
    Iterable<User> findAll();
}
