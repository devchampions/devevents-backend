package com.devchampions.app.membership;

import com.devchampions.infrastructure.Reaction;
import org.springframework.stereotype.Component;

@Component
class BecomeAMemberReaction implements Reaction<BecomeAMember, BecomeAMember.MemberId> {

    private final MemberRepository members;

    public BecomeAMemberReaction(MemberRepository members) {
        this.members = members;
    }

    @Override
    public BecomeAMember.MemberId react(BecomeAMember $) {

        Member member = new Member($.fullName());
        members.save(member);

        return new BecomeAMember.MemberId(member.uuid());
    }
}
