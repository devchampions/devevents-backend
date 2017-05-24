package com.devchampions.app.event.addnew;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class City {

    @AttributeOverride(name = "name", column = @Column(name = "ADMIN_NAME"))
    private Administrative administrative;

    @AttributeOverride(name = "name", column = @Column(name = "COUNTRY_NAME"))
    private Country country;

    private String name;

    public City(String name, Country country) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("City name is missing");
        }
        if (country == null) {
            throw new IllegalArgumentException("Country is missing");
        }
        this.country = country;
    }

    private City() {
    }

    public void enclose(Administrative administrative) {
        if (administrative == null) {
            throw new IllegalArgumentException("Administrative is missing");
        }
        this.administrative = administrative;
    }
}
