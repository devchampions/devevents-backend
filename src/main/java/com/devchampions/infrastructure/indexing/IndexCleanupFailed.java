package com.devchampions.infrastructure.indexing;

public class IndexCleanupFailed extends RuntimeException {

    public IndexCleanupFailed(Throwable cause) {
        super(cause);
    }
}
