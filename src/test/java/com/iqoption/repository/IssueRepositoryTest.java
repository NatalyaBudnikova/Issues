package com.iqoption.repository;

import com.iqoption.domain.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository repository = new IssueRepository();
    Issue issue1 = new Issue(1, true, "AlexeyCh", "Vasiliy", "deploy", "forex", Set.of("int", "prod"), LocalDateTime.of(2019, 9, 10, 14, 33));
    Issue issue2 = new Issue(2, true, "Vasiliy", "Sergey", "test","crypto", Set.of("sb", "int"), LocalDateTime.of(2020, 9, 10, 14, 33));
    Issue issue3 = new Issue(3, false, "Igor", "AlexeySh", "release", "portfolio", null, LocalDateTime.of(2020, 9, 10, 14, 33));
    Issue issue4 = new Issue(4, true, "Denis", "Igor", "dev", "IB", null, LocalDateTime.now());
    Issue issue5 = new Issue(5, true, "Denis", "Igor", "dev", "IB", Set.of("sb", "int"), LocalDateTime.of(2018, 9, 10, 14, 33));

    @BeforeEach
    void setUp() {
        repository.save(issue1);
        repository.save(issue2);
        repository.save(issue3);
        repository.save(issue4);
        repository.save(issue5);
    }

    @Test
    void removeById() {
        repository.removeById(2);
        assertNull(repository.findByID(2));
    }

    @Test
    void removeAll() {
        List<Issue> expected = List.of();
        repository.removeAll();
        assertEquals(expected, repository.getAll());
    }

    @Test
    void findById() {
        assertEquals(issue2, repository.findByID(2));
    }

    @Test
    void findByIdNotFind() {
        assertNull(repository.findByID(8));
    }

}