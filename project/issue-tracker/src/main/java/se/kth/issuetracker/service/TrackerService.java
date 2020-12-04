package se.kth.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.repository.UserRepository;

@Service
public class TrackerService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
