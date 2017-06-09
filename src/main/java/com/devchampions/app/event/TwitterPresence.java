package com.devchampions.app.event;

import javax.persistence.Embeddable;

@Embeddable
public class TwitterPresence {

    private String handle;
    private String hashtag;

    public TwitterPresence(String handle, String hashtag) {
        if (handle == null) {
            throw new IllegalArgumentException("Twitter handle is missing");
        }
        if (hashtag == null) {
            throw new IllegalArgumentException("Twitter hashtag is missing");
        }
        this.handle = handle;
        this.hashtag = hashtag;
    }

    private TwitterPresence() {
    }

    public String handle() {
        return handle;
    }

    public String hashtag() {
        return hashtag;
    }
}
