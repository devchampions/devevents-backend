package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static java.lang.System.out;

@RestController("/events")
class AddNewEventEndpoint {

    @Autowired
    Now now;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> hit(@RequestBody AddNewEvent addNewEvent, UriComponentsBuilder uri) {
        out.println(addNewEvent);

        AddNewEvent.R response = addNewEvent.execute(now);
        return created(uri, response.eventId());

    }

    private ResponseEntity<Void> created(UriComponentsBuilder uri, UUID eventId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri.path("/events/{id}").buildAndExpand(eventId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
