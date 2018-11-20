package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;


@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        System.out.println("Create out: " +timeEntryToCreate.getProjectId());
        ResponseEntity<TimeEntry> timeEntryResponseEntity =
                new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

        return timeEntryResponseEntity;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<TimeEntry> read(long timeEntryId) {

        ResponseEntity<TimeEntry> timeEntryResponseEntity;

        if(timeEntryRepository.find(timeEntryId) == null){
            timeEntryResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            timeEntryResponseEntity =
                    new ResponseEntity<>(timeEntryRepository.find(timeEntryId), HttpStatus.OK);
        }

        return  timeEntryResponseEntity;
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();
        ResponseEntity<List<TimeEntry>> timeEntryResponseList = new ResponseEntity<>(timeEntries, HttpStatus.OK);
        return timeEntryResponseList;
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {
        ResponseEntity<TimeEntry> timeEntryResponseEntity;

        if(timeEntryRepository.update(timeEntryId, expected) == null){
            timeEntryResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            timeEntryResponseEntity =
                    new ResponseEntity<>(timeEntryRepository.update(timeEntryId, expected), HttpStatus.OK);
        }

        return timeEntryResponseEntity;
    }

    public ResponseEntity delete(long timeEntryId) {

        ResponseEntity<TimeEntry> timeEntryResponseEntity =
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

        timeEntryRepository.delete(timeEntryId);

       return timeEntryResponseEntity;
=======
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntriesRepo.create(timeEntry);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntriesRepo.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntriesRepo.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
>>>>>>> b084c49... Add TimeEntry MVC in memory
    }
}
