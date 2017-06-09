package com.devchampions.app.membership;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    private String fullName;

    public Member(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("Member name is missing");
        }
        this.fullName = fullName;
    }


    private Member() {
    }

    public String uuid() {
        return uuid;
    }

    public String fullName() {
        return fullName;
    }
}
