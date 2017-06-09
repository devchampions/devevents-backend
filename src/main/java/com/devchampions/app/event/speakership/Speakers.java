package com.devchampions.app.event.speakership;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Speakers extends ForwardingSet<Speaker> {

    private final Set<Speaker> items = new HashSet<>();

    @Override
    protected Set<Speaker> delegate() {
        return items;
    }
}
