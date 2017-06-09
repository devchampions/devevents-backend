package com.devchampions.app.event.speakership;

import com.devchampions.app.event.addnew.Event;
import com.devchampions.app.event.addnew.EventRepository;
import com.devchampions.app.membership.Member;
import com.devchampions.app.membership.MemberRepository;
import com.devchampions.infrastructure.Command;
import com.devchampions.infrastructure.Reaction;
import org.springframework.stereotype.Component;

@Component
class BecomeASpeakerReaction implements Reaction<BecomeASpeaker, Command.R.Void> {

    private final EventRepository events;
    private final MemberRepository members;

    public BecomeASpeakerReaction(EventRepository events, MemberRepository members) {
        this.events = events;
        this.members = members;
    }

    @Override
    public Command.R.Void react(BecomeASpeaker $) {

        Event event = events
                .findByUuid($.eventId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find event by unique id " + $.eventId()));

        Member member = members
                .findByUuid($.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find member by unique id " + $.memberId()));

        Speaker speaker = new Speaker(member.uuid(), member.fullName());
        event.accept(speaker);

        return new Command.R.Void();
    }
}
