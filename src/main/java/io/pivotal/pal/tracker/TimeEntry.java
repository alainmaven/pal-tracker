package io.pivotal.pal.tracker;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.Objects;

public class TimeEntry {

=======

public class TimeEntry {
>>>>>>> b084c49... Add TimeEntry MVC in memory
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

<<<<<<< HEAD
=======
    public TimeEntry() {
    }

>>>>>>> b084c49... Add TimeEntry MVC in memory
    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

<<<<<<< HEAD
    public TimeEntry() {}

    public Long getId() {
        return id;
    }

=======
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

>>>>>>> b084c49... Add TimeEntry MVC in memory
    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    @Override
<<<<<<< HEAD
    public String toString() {
        return "TimeEntry{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return id == timeEntry.id &&
                projectId == timeEntry.projectId &&
                userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                Objects.equals(date, timeEntry.date);
=======
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (id != timeEntry.id) return false;
        if (projectId != timeEntry.projectId) return false;
        if (userId != timeEntry.userId) return false;
        if (hours != timeEntry.hours) return false;
        return date != null ? date.equals(timeEntry.date) : timeEntry.date == null;
>>>>>>> b084c49... Add TimeEntry MVC in memory
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(id, projectId, userId, date, hours);
=======
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (projectId ^ (projectId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + hours;
        return result;
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", userId=" + userId +
            ", date='" + date + '\'' +
            ", hours=" + hours +
            '}';
>>>>>>> b084c49... Add TimeEntry MVC in memory
    }
}
