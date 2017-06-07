package com.devchampions.app.event.addnew;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends org.springframework.data.repository.Repository<Event, String> {

    Optional<Event> findOne(String id);

    void save(Event event);

}
