package se.kth.issuetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.issuetracker.entity.Issue;
import se.kth.issuetracker.entity.IssueStatus;
import se.kth.issuetracker.repository.IssueRepository;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    public Issue findById(int id) {
        return issueRepository.findById(id);
    }

    @Transactional
    public void updateById(int id, Issue issue) {
        String title = issue.getTitle();
        String description = issue.getDescription();
        IssueStatus status = issue.getStatus();
        issueRepository.updateById(id, title, description, status);
    }

    @Transactional
    public void deleteById(int id) {
        issueRepository.deleteById(id);
    }
}
