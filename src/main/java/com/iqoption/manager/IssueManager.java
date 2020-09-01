package com.iqoption.manager;

import com.iqoption.domain.Issue;
import com.iqoption.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue item) {
        repository.save(item);
    }

    public  void removeById(int id) {
        repository.removeById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public Issue findById(int id) {
        return repository.findByID(id);
    }

    public List<Issue> findAll() {
        return repository.getAll();
    }

    public List<Issue> findOpenedIssue() {
        List<Issue> issues = repository.getAll();
        return issues.stream()
                .filter(issue -> issue.isOpen())
                .collect(Collectors.toList());
    }

    public List<Issue> findClosedIssue() {
        List<Issue> issues = repository.getAll();
        return issues.stream()
                .filter(issue -> !issue.isOpen())
                .collect(Collectors.toList());
    }

    public List<Issue> searchBy(Predicate<Issue> filter) {
        return repository.getAll().stream()
                .filter(filter)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Issue> searchByAssignee(String assignee) {
        return searchBy(issue -> issue.getAssignee().equalsIgnoreCase(assignee));
    }

    public List<Issue> searchByAuthor(String author) {
        return searchBy(issue -> issue.getAuthor().equalsIgnoreCase(author));
    }

    public List<Issue> searchByLabel(String label) {
        return searchBy(issue -> Objects.nonNull(issue.getLabels()) && issue.getLabels().contains(label.toLowerCase()));
    }

    public void closeIssue(int issueId) {
        Issue item = repository.findByID(issueId);
        if (item.isOpen()) {
            item.setOpen(false);
        }
    }

    public void openIssue(int issueId) {
        Issue item = repository.findByID(issueId);
        if (!item.isOpen()) {
            item.setOpen(true);
        }
    }
}
