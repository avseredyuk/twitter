package ua.rd.twitter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Repository("userRepository")
public class InMemUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Autowired
    private ArrayList<User> testUsers;

    @PostConstruct
    public void init(){
        users.addAll(testUsers);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Iterable<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User find(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .get();
    }

    @Override
    public User find(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();
    }
}
