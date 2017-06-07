package com.devchampions.app.event.view;

import com.devchampions.app.event.addnew.EventRepository;
import com.devchampions.infrastructure.Reaction;
import org.springframework.stereotype.Component;

@Component
public class ViewEventReaction implements Reaction<ViewEvent, ViewEvent.Event> {

    private final EventRepository eventRepository;

    public ViewEventReaction(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ViewEvent.Event react(ViewEvent $) {
        return eventRepository.findByUuid($.id())
                .map(e -> {
                    ViewEvent.Event event = new ViewEvent.Event();
                    event.name = e.name();
                    event.about = e.about();
                    event.startsOnEpoch = e.startsOn().toEpochDay();
                    event.endsOnEpoch = e.endsOn().toEpochDay();
                    event.website = e.website();
                    event.tags = e.tags();
                    return event;
                })
                .orElseThrow(() -> new IllegalArgumentException("Cannot find event by unique id " + $.id()));
    }
}
