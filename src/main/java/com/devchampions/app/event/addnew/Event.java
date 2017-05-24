package com.devchampions.app.event.addnew;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
class Event {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    public Event(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Event name is missing");
        }
        this.name = name;
    }

    private Event() {
    }

    public UUID id() {
        return id;
    }
}
