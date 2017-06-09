package com.devchampions.infrastructure;

import java.lang.reflect.Type;

public interface Command<T extends Command.R> {

    default Type type() {
        return getClass();
    }

    interface R {
        class Void implements R {

        }
    }

    default T execute(Now now) {
        return now.execute(this);
    }

}
