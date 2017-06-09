package com.devchampions.app.event.view;

import com.devchampions.app.event.TwitterPresence;
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
                    event.startsOn = e.startsOn().toString();
                    event.endsOn = e.endsOn().toString();
                    event.location = e.city().name() + ", " + e.city().country().name();
                    event.website = e.website();
                    event.tags = e.tags();
                    event.twitterHandle = e.twitter().map(TwitterPresence::handle).orElse("");
                    event.twitterHashtag = e.twitter().map(TwitterPresence::hashtag).orElse("");
                    return event;
                })
                .orElseThrow(() -> new IllegalArgumentException("Cannot find event by unique id " + $.id()));
    }
}
