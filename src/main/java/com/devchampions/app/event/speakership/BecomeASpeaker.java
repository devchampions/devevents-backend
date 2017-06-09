package com.devchampions.app.event.speakership;

import com.devchampions.infrastructure.Command;

class BecomeASpeaker implements Command<Command.R.Void> {

    private final String eventId;
    private final String memberId;

    public BecomeASpeaker(String eventId, String memberId) {
        this.eventId = eventId;
        this.memberId = memberId;
    }

    public String eventId() {
        return eventId;
    }

    public String memberId() {
        return memberId;
    }
}
