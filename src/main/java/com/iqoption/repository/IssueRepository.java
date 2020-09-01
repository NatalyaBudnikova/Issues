package com.iqoption.repository;

import com.iqoption.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    private List<Issue> items = new ArrayList<>();

    public void save(Issue item) {
        items.add(item);
    }

    public Issue findByID(int id) {
        Issue findItem = null;
        for (Issue item : items) {
            if (item.getId() == id) {
                findItem = item;
                break;
            }
        }
        return findItem;
    }

    public void removeById(int id) {
        items.removeIf((i) -> i.getId() == id);
    }


    public void removeAll() {
        items.clear();
    }

    public List<Issue> getAll() {
        return items;
    }
}
