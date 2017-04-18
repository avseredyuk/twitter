package ua.rd.twitter.repository;

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

    @PostConstruct
    public void init(){
        users.add(new User("vasya", 14));
        users.add(new User("petya", 16));
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
    public User findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .get();
    }
}
