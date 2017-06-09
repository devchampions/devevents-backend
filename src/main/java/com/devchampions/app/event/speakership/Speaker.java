package com.devchampions.app.event.speakership;

public class Speaker {

    private final String uuid;

    private final String fullName;

    public Speaker(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String uuid() {
        return uuid;
    }

    public String fullName() {
        return fullName;
    }
}
