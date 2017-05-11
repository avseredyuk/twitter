package ua.rd.twitter.repository;

import ua.rd.twitter.domain.User;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
public interface UserRepository {
    void save(User user);
    void update(User user);
    User find(String name);
    void delete(User user);
    List<User> findAll();
    List<User> findAllByUsernameList(List<String> userNames);
}
