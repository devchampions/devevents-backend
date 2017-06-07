package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.indexing.Index;
import com.devchampions.infrastructure.indexing.IndexedWithSuppliedId;
import com.google.common.collect.ImmutableList;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    private String name;
    private String about;
    private String website;

    private LocalDate startsOn;
    private LocalDate endsOn;

    @ElementCollection
    private Collection<String> tags = new LinkedList<>();

    @AttributeOverride(name = "name", column = @Column(name = "CITY_NAME"))
    @Embedded
    private City city;

    public Event(String name, City city, LocalDate startsOn) {
        rename(name);
        place(city);
        startOn(startsOn);
        endOnTheSameDay();
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

    public LocalDate startsOn() {
        return startsOn;
    }

    public void endOn(LocalDate endsOn) {
        if (endsOn == null) {
            throw new IllegalArgumentException("Ending date is missing");
        }
        this.endsOn = endsOn;
    }

    public void endOnTheSameDay() {
        this.endsOn = startsOn;
    }

    public void describe(String about) {
        this.about = about;
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

    public String uuid() {
        return uuid;
    }


    
    public Index<Indexed> index() {
        Indexed indexed = new Indexed();
        indexed.entityId = uuid;
        indexed.name = name;
        indexed.city = capitalizeFully(city.name());
        indexed.country = capitalizeFully(city.country().name());
        indexed.administrative = city.administrative().map(Administrative::name).orElse("");
        indexed.month = capitalizeFully(startsOn.getMonth().name());
        indexed.year = Integer.toString(startsOn.getYear());
        indexed.startsOnDay = startsOn.getDayOfMonth();
        indexed.endsOnEpoch = endsOn.toEpochDay();

        if (!startsOn.isEqual(endsOn)) {
            indexed.endsOnDay = endsOn.getDayOfMonth();
        }

        indexed.tags = tags;
        indexed.about = about;
        indexed.rank = 0;
        indexed.rating = 0;

        Index.Simple<Indexed> index = new Index.Simple<>("events", indexed);
        index.rankBy("desc(rank)", "desc(rating)");
        index.relevanceBy("name", "city", "administrative", "country", "month", "year", "tags", "about");
        return index;
    }

    public String name() {
        return name;
    }

    public String about() {
        return about;
    }

    public String website() {
        return website;
    }

    public Collection<String> tags() {
        return ImmutableList.copyOf(tags);
    }

    public LocalDate endsOn() {
        return endsOn;
    }

    static class Indexed implements IndexedWithSuppliedId {
        public String entityId;
        public String name;
        public String about;
        public String city;
        public String country;
        public String administrative;
        public Collection<String> tags;
        public String month;
        public String year;

        public double rank;
        public double rating;
        public Integer startsOnDay;
        public Integer endsOnDay;
        public Long endsOnEpoch;

        @Override
        public String id() {
            return entityId;
        }
    }
}
