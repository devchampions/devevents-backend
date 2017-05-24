package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Reaction;
import com.devchampions.infrastructure.indexing.Indexer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class AddNewEventReaction implements Reaction<AddNewEvent, AddNewEvent.R> {

    private final EventRepository repository;
    private final Indexer indices;

    public AddNewEventReaction(EventRepository repository, Indexer indices) {
        this.repository = repository;
        this.indices = indices;
    }

    @Override
    public AddNewEvent.R react(AddNewEvent $) {
        Event event = new Event($.name, city($), $.startsOn);
        $.description.ifPresent(event::describe);
        $.website.ifPresent(event::hyperlink);
        $.endsOn.ifPresent(event::endOn);

        Arrays.stream($.tags).forEach(event::tag);
        repository.save(event);
        indices.append(event.index());

        return new AddNewEvent.R(event.id());
    }

    private City city(AddNewEvent $) {
        Country country = new Country($.country);
        City city = country.city($.city);
        $.administrative.map(Administrative::new).ifPresent(city::enclose);
        return city;
    }
}


