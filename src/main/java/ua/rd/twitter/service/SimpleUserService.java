package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.rd.twitter.domain.Timeline;
import ua.rd.twitter.domain.User;
import ua.rd.twitter.repository.UserRepository;

import java.util.List;

/**
 * Created by Anton_Serediuk on 4/14/2017.
 */
@Service("userService")
public class SimpleUserService implements UserService {
    private final UserRepository userRepository;
    private final TimelineService timelineService;

    @Autowired
    public SimpleUserService(UserRepository userRepository, TimelineService timelineService) {
        this.userRepository = userRepository;
        this.timelineService = timelineService;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
        timelineService.create(user);
    }

    @Override
    public void edit(User user) {
        userRepository.edit(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByUsernameList(List<String> userNames) {
        return userRepository.findAllByUsernameList(userNames);
    }

    @Override
    public User find(String name) {
        return userRepository.find(name);
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }
}
