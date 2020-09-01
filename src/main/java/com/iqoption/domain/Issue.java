package com.iqoption.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor

public class Issue implements Comparable<Issue>{
    private int id;
    private boolean open;
    private String author;
    private String assignee;
    private String summary;
    private String description;
    private Set<String> labels;
    private LocalDateTime openedAt;

    @Override
    public int compareTo(Issue o) {
        return this.openedAt.compareTo(o.openedAt);
    }


}
