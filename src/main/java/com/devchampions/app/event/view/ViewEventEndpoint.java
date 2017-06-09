package com.devchampions.app.event.view;

import com.devchampions.infrastructure.Now;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewEventEndpoint {

    private final Now now;

    public ViewEventEndpoint(Now now) {
        this.now = now;
    }


    @GetMapping("/events/{id}")
    ViewEvent.Event hit(@PathVariable String id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication " + authentication);



        ViewEvent viewEvent = new ViewEvent(id);
        return viewEvent.execute(now);
    }

}
