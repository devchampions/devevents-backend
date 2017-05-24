package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AddNewEventReaction implements Reaction<AddNewEvent, AddNewEvent.R> {

    @Autowired
    EventRepository eventRepository;

    @Override
    public AddNewEvent.R react(AddNewEvent $) {

        Event event = new Event($.name);
        eventRepository.save(event);

        return new AddNewEvent.R(event.id());
    }
}


