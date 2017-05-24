package com.devchampions.app.event.addnew;

import com.devchampions.infrastructure.Command;

import java.util.UUID;

public class AddNewEvent implements Command<AddNewEvent.R> {

//    public enum Iam {
//        Tracking, Attending, Organising, Speaking
//    }

    public String name;

    public String description;
//    public String website;
//
//    public String locationId;
//    public String seriesId;
//    public String[] topicIds;
//
//    public LocalDate startsOn;
//    public LocalDate endsOn;
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
