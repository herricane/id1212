package se.kth.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.repository.UserRepository;

@Service
public class TrackerService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (username.equals("") || password.equals("") || userRepository.findByUsername(username) != null) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public User getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
