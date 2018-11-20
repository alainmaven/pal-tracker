package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
<<<<<<< HEAD

    public TimeEntry create(TimeEntry timeEntry);
    public TimeEntry find(long id);
    public List<TimeEntry> list();
    public TimeEntry update(long id, TimeEntry timeEntry);
    public void delete(long id);
=======
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(Long id);
    List<TimeEntry> list();
    TimeEntry update(Long id, TimeEntry timeEntry);
    void delete(Long id);
>>>>>>> b084c49... Add TimeEntry MVC in memory
}
