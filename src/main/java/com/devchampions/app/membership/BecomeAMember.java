package com.devchampions.app.membership;

import com.devchampions.infrastructure.Command;

class BecomeAMember implements Command<BecomeAMember.MemberId> {

    private final String fullName;

    public BecomeAMember(String fullName) {
        this.fullName = fullName;
    }

    public String fullName() {
        return fullName;
    }

    public static class MemberId implements Command.R {

        private final String id;

        public MemberId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

}
