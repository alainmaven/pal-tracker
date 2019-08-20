package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private ConcurrentHashMap<Long, TimeEntry> store = new ConcurrentHashMap<>();
    private AtomicInteger currentId = new AtomicInteger(0);

    public TimeEntry create(TimeEntry timeEntry) {

        TimeEntry createdTimeEntry = new TimeEntry(currentId.incrementAndGet(), timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        store.put(createdTimeEntry.getId(), createdTimeEntry);

        return createdTimeEntry;
    }

    public TimeEntry find(long id) {
        return store.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry OldValue = store.replace(id, new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()));

        return store.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>(store.values());
        return list;
    }

    public void delete(long id) {
        store.remove(id);
    }
}
