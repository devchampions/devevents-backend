package com.devchampions.infrastructure.indexing;

public class IndexingFailed extends RuntimeException {

    public IndexingFailed(Throwable cause) {
        super(cause);
    }
}
