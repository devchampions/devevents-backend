package com.devchampions.app.event.view;

import com.devchampions.infrastructure.Command;

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
    }

}
