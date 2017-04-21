package ua.rd.twitter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.twitter.domain.User;

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
    public void edit(User user) {
        for (int i = 0; i < users.size(); i++) {
            User curUser = users.get(i);
            if (curUser.getId().equals(user.getId())) {
                users.set(i, user);
            }
        }
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

    @Override
    public User find(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();
    }
}
