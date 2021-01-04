package se.kth.issuetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.issuetracker.entity.Issue;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/issues")
    public List<Issue> getIssuesByUserId(@PathVariable int id) {
        return userService.findAllIssuesByUserId(id);
    }

    @PostMapping("/{id}/issues")
    public Issue postIssueByUserId(@PathVariable int id, @RequestBody Issue issue) {
        return userService.addIssueByUserId(id, issue);
    }
}
