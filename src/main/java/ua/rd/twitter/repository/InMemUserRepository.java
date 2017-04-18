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
        users.add(new User("vasya"));
        users.add(new User("petya"));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Iterable<User> findAll() {
        return new ArrayList<>(users);
    }
}
