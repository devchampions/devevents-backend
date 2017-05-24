package com.devchampions.app.event.addnew;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

@Entity
class Event {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    private String description;
    private String website;

    private LocalDate startsOn;
    private LocalDate endsOn;

    private String address;

    @ElementCollection
    private Collection<String> tags = new LinkedList<>();

    @AttributeOverride(name = "name", column = @Column(name = "CITY_NAME"))
    @Embedded
    private City city;

    public Event(String name, City city, LocalDate startsOn) {
        rename(name);
        place(city);
        startOn(startsOn);
    }

    private Event() {
    }

    public void tag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("Tag is missing");
        }
        this.tags.add(tag);
    }

    public void startOn(LocalDate startsOn) {
        if (startsOn == null) {
            throw new IllegalArgumentException("Starting date is missing");
        }
        this.startsOn = startsOn;
    }

    public void endOn(LocalDate endsOn) {
        this.endsOn = endsOn;
    }

    public void describe(String description) {
        this.description = description;
    }

    public void rename(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Event name is missing");
        }
        this.name = name;
    }

    public void place(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City is missing");
        }
        this.city = city;
    }

    public void hyperlink(String website) {
        this.website = website;
    }

    public UUID id() {
        return id;
    }
}
