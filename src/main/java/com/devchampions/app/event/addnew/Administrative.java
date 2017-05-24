package com.devchampions.app.event.addnew;

import javax.persistence.Embeddable;

@Embeddable
public class Administrative {

    private String name;

    public Administrative(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Administrative name is missing");
        }
        this.name = name;
    }

    private Administrative() {
    }
}
