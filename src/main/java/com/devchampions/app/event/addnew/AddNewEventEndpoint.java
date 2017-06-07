package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Now;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static java.lang.System.out;

@RestController("/events")
class AddNewEventEndpoint {

    private final Now now;

    public AddNewEventEndpoint(Now now) {
        this.now = now;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> hit(@RequestBody AddNewEvent addNewEvent, UriComponentsBuilder uri) {
        out.println(addNewEvent);
        AddNewEvent.EventId eventId = addNewEvent.execute(now);
        return created(uri, eventId.toString());

    }

    @RequestMapping("events/secured")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> hitSecured(@RequestBody AddNewEvent addNewEvent, UriComponentsBuilder uri) {
        out.println(addNewEvent);
        out.println("hello secured $addNewEvent");
        AddNewEvent.R response = addNewEvent.execute(now);
        return created(uri, response.toString());
    }

    private ResponseEntity<Void> created(UriComponentsBuilder uri, String eventId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri.path("/events/{id}").buildAndExpand(eventId).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
