package se.kth.issuetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.service.TrackerService;

@RestController
@RequestMapping("/api")
public class TrackerController {
    @Autowired
    private TrackerService trackerService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return trackerService.addUser(user);
    }


}
