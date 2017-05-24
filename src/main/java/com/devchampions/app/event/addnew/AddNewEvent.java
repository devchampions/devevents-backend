package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Command;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class AddNewEvent implements Command<AddNewEvent.R> {

//    public enum Iam {
//        Tracking, Attending, Organising, Speaking
//    }

    public String name;

    public String city;
    public String country;
    public Optional<String> administrative = Optional.empty();

    public Optional<String> description = Optional.empty();
    public Optional<String> website = Optional.empty();
    //
//    public String seriesId;
    public String[] tags = new String[]{};
    //
    public LocalDate startsOn;
    public Optional<LocalDate> endsOn = Optional.empty();
//
//    public String twitterHandle;
//    public String twitterHashtag;
//
//    public Iam iam;

    static class R implements Command.R {

        private final UUID eventId;

        public R(UUID eventId) {
            this.eventId = eventId;
        }

        public UUID eventId() {
            return eventId;
        }
    }

}
