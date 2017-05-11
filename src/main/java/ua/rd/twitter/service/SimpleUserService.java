package ua.rd.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void save(User user) {
        userRepository.save(user);
        timelineService.create(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        timelineService.delete(user);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> findAllByUsernameList(List<String> userNames) {
        return userRepository.findAllByUsernameList(userNames);
    }

    @Override
    @Transactional
    public User find(String name) {
        return userRepository.find(name);
    }

}
