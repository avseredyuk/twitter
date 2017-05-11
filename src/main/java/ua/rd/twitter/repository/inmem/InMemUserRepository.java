package ua.rd.twitter.repository.inmem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Repository("userRepository")
public class InMemUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            User curUser = users.get(i);
            if (curUser.getId().equals(user.getId())) {
                users.set(i, user);
            }
        }
    }

    @Override
    public void delete(User user) {
        users = users.stream()
//                .filter(u -> u.getName() != null)
                .filter(u -> !u.getName().equals(user.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public List<User> findAllByUsernameList(List<String> userNames) {
        return userNames.stream()
                .map(this::find)
                .collect(Collectors.toList());
    }

    @Override
    public User find(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .get();
    }
}
