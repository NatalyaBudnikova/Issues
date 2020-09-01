package com.iqoption.manager;

import com.iqoption.domain.Issue;
import com.iqoption.repository.IssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    IssueManager manager = new IssueManager(new IssueRepository());
    Issue issue1 = new Issue(1, true, "AlexeyCh", "Vasiliy", "deploy", "forex", Set.of("int", "prod"), LocalDateTime.of(2019, 9, 10, 14, 33));
    Issue issue2 = new Issue(2, true, "Vasiliy", "Sergey", "test","crypto", Set.of("sb", "int"), LocalDateTime.of(2020, 9, 10, 14, 33));
    Issue issue3 = new Issue(3, false, "Igor", "AlexeySh", "release", "portfolio", null, LocalDateTime.of(2020, 9, 10, 14, 33));
    Issue issue4 = new Issue(4, true, "Denis", "Igor", "dev", "IB", null, LocalDateTime.now());
    Issue issue5 = new Issue(5, true, "Denis", "Igor", "dev", "IB", Set.of("sb", "int"), LocalDateTime.of(2018, 9, 10, 14, 33));

    @BeforeEach
    void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    void findById() {
        assertEquals(issue2, manager.findById(2));
    }

    @Test
    void findByIdNull() {
        assertNull(manager.findById(15));
    }

    @Test
    void removeAll() {
        List<Issue> expected = List.of();
        manager.removeAll();
        assertEquals(expected, manager.findAll());
    }

    @Test
    void removeById() {
        List<Issue> expected = List.of(issue1, issue2, issue4, issue5);
        manager.removeById(3);
        assertEquals(expected, manager.findAll());
    }

    @Test
    void findAll() {
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);
        assertEquals(expected, manager.findAll());
    }

    @Test
    void searchByAssignee() {
        List<Issue> expected = List.of(issue4, issue5);
        List<Issue> actual = manager.searchByAssignee("Igor");
        assertEquals(expected, actual);
    }

    @Test
    void notSearchByAssignee() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.searchByAssignee("Dasha");
        assertEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        List<Issue> expected = List.of(issue2);
        List<Issue> actual = manager.searchByAuthor("Vasiliy");
        assertEquals(expected, actual);
    }

    @Test
    void notSearchByAuthor() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.searchByAuthor("Dasha");
        assertEquals(expected, actual);
    }

    @Test
    void searchByLabel() {
        List<Issue> expected = List.of(issue2, issue1, issue5);
        List<Issue> actual = manager.searchByLabel("int");
        assertEquals(expected, actual);
    }

    @Test
    void notSearchByLabel() {
        List<Issue> expected = List.of();
        List<Issue> actual = manager.searchByLabel("integration");
        assertEquals(expected, actual);
    }

    @Test
    void findOpenedIssues() {
        List<Issue> expected = List.of(issue1, issue2, issue4, issue5);
        assertEquals(expected, manager.findOpenedIssue());
    }

    @Test
    void findClosedIssues() {
        List<Issue> expected = List.of(issue3);
        assertEquals(expected, manager.findClosedIssue());
    }

    @Test
    void openIssue() {
        manager.openIssue(3);
        assertTrue(issue3.isOpen());
    }

    @Test
    void closeIssue() {
        manager.closeIssue(2);
        assertFalse(issue2.isOpen());
    }
}