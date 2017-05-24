package com.devchampions.infrastructure;

import java.lang.reflect.Type;

public class NoReactionFound extends RuntimeException {

    private final Type commandType;

    public NoReactionFound(Type commandType) {
        this.commandType = commandType;
    }

    @Override
    public String getLocalizedMessage() {
        return "Cannot find reaction for command " + commandType;
    }
}
