package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    }
}
