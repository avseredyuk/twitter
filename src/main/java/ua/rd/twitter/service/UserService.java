package ua.rd.twitter.service;

import ua.rd.twitter.domain.User;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
public interface UserService {
    void save(User user);
    List<User> findAllByUsernameList(List<String> userNames);
    Iterable<User> findAll();
    User find(String name);
    User find(Long id);
}
