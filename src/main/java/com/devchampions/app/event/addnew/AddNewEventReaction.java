package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class AddNewEventReaction implements Reaction<AddNewEvent, AddNewEvent.R> {

    @Autowired
    EventRepository eventRepository;

    @Override
    public AddNewEvent.R react(AddNewEvent $) {
        Event event = new Event($.name, city($), $.startsOn);
        $.description.ifPresent(event::describe);
        $.website.ifPresent(event::hyperlink);
        $.endsOn.ifPresent(event::endOn);

        Arrays.stream($.tags).forEach(event::tag);
        eventRepository.save(event);

        return new AddNewEvent.R(event.id());
    }

    private City city(AddNewEvent $) {
        Country country = new Country($.country);
        City city = country.city($.city);
        $.administrative.map(Administrative::new).ifPresent(city::enclose);
        return city;
    }
}


