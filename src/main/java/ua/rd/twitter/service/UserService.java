package ua.rd.twitter.service;

import ua.rd.twitter.domain.User;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
public interface UserService {
    void save(User user);
    Iterable<User> findAll();
    User findByName(String name);
}
