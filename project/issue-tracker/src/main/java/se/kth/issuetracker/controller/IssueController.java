package se.kth.issuetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.issuetracker.entity.Issue;
import se.kth.issuetracker.service.IssueService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @GetMapping("")
    public List<Issue> getAllIssues() {
        return issueService.findAll();
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable int id) {
        return issueService.findById(id);
    }

    @PutMapping("/{id}")
    public Issue modifyIssueById(@PathVariable int id, @RequestBody Issue issue) {
        issueService.updateById(id, issue);
        return issueService.findById(id);
    }

    @DeleteMapping("/{id}")
    public List<Issue> deleteIssueById(@PathVariable int id) {
        issueService.deleteById(id);
        return issueService.findAll();
    }

    @PutMapping("/{id}/{userId}")
    public Issue modifyAssignedUser(@PathVariable int id, @PathVariable int userId) {
        issueService.updateUserById(id, userId);
        return issueService.findById(id);
    }
}
