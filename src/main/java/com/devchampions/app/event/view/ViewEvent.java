package com.devchampions.app.event.view;

import com.devchampions.infrastructure.Command;

import java.util.Collection;

public class ViewEvent implements Command<ViewEvent.Event> {

    private final String id;

    public ViewEvent(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public static class Event implements Command.R {
        public String name;
        public String about;
        public String website;
        public long startsOnEpoch;
        public long endsOnEpoch;
        public Collection<String> tags;
    }

}
