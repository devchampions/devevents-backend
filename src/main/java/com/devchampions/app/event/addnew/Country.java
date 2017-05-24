package com.devchampions.app.event.addnew;

import javax.persistence.Embeddable;

@Embeddable
public class Country {

    private String name;

    public Country(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Country name is missing");
        }
        this.name = name;
    }

    private Country() {
    }

    public City city(String name) {
        return new City(name, this);
    }
}
