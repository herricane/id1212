package se.kth.issuetracker.controller;

import com.sun.xml.txw2.output.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.issuetracker.entity.User;
import se.kth.issuetracker.response.Response;
import se.kth.issuetracker.response.ResponseFactory;
import se.kth.issuetracker.service.TrackerService;

@RestController
@RequestMapping("/api")
public class TrackerController {
    @Autowired
    private TrackerService trackerService;

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        boolean isNew = trackerService.addUser(user);
        if (isNew) {
            return ResponseFactory.buildSuccessResponse(user.getUsername());
        } else {
            return ResponseFactory.buildFailResponse();
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        User user = trackerService.getUser(username, requestUser.getPassword());
        if (user == null) {
            return ResponseFactory.buildFailResponse();
        } else {
            return ResponseFactory.buildSuccessResponse(username);
        }
    }

}
