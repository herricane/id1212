package se.kth.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.issuetracker.entity.Issue;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.repository.IssueRepository;
import se.kth.issuetracker.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public List<Issue> findAllIssuesByUserId(int id) {
        return issueRepository.findAllByUserId(id);
    }

    public Issue addIssueByUserId(int id, Issue issue) {
        User user = userRepository.findById(id);
        issue.setUser(user);
        return issueRepository.save(issue);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
